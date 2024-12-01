package com.example.mynews.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mynews.R
import com.example.mynews.domain.model.Article
import com.example.mynews.presentation.bookmark.BookmarkScreen
import com.example.mynews.presentation.bookmark.BookmarkViewModel
import com.example.mynews.presentation.details.DetailsEvent
import com.example.mynews.presentation.details.DetailsScreen
import com.example.mynews.presentation.details.DetailsViewModel
import com.example.mynews.presentation.home.HomeScreen
import com.example.mynews.presentation.home.HomeViewModel
import com.example.mynews.presentation.navgraph.Route
import com.example.mynews.presentation.news_navigator.components.BottomNavigationItem
import com.example.mynews.presentation.news_navigator.components.NewsBottomNavigation
import com.example.mynews.presentation.search.SearchScreen
import com.example.mynews.presentation.search.SearchViewModel

@Composable
fun NewsNavigator(modifier: Modifier = Modifier) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(
                R.drawable.ic_home,
                text = "Home"
            ),
            BottomNavigationItem(
                R.drawable.ic_search,
                text = "Search"
            ),
            BottomNavigationItem(
                R.drawable.ic_bookmark,
                text = "Bookmarks"
            )
        )
    }

    val navController= rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value

    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when(
        backStackState?.destination?.route
    ){
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route ->1
        Route.BookmarkScreen.route->2
        else ->0
    }

    Scaffold(
        modifier=Modifier.fillMaxSize(),
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = {index->
                    when(index){
                        0-> navigateToTop(navController,Route.HomeScreen.route)
                        1-> navigateToTop(navController,Route.SearchScreen.route)
                        2-> navigateToTop(navController,Route.BookmarkScreen.route)
                    }
                }

                )
        }
    ) { it ->
        val bottomPadding = it.calculateBottomPadding()

     NavHost(
         navController = navController,
         startDestination = Route.HomeScreen.route,
         modifier=Modifier.padding(bottom = bottomPadding)
     ) {
         composable(route = Route.HomeScreen.route){
             val viewModel: HomeViewModel = hiltViewModel()
             val articles = viewModel.news.collectAsLazyPagingItems()
             
             HomeScreen(
                 articles = articles,
                 navigateToSearch = {
                     navigateToTop(navController,Route.SearchScreen.route)
                 },
                 navigateToDetails = {
                     navigateToTop(navController,Route.DetailsScreen.route)
                 }
             )
         }

         composable(route = Route.HomeScreen.route){
             val viewModel: HomeViewModel = hiltViewModel()
             val articles = viewModel.news.collectAsLazyPagingItems()

             HomeScreen(
                 articles = articles,
                 navigateToSearch = {
                     navigateToTop(navController,Route.SearchScreen.route)
                 },
                 navigateToDetails = {article->
                     navigateToDetails(
                         navController,article
                     )
                 }
             )
         }

         composable(route=Route.SearchScreen.route){
             val viewModel: SearchViewModel = hiltViewModel()
             SearchScreen(
                 state = viewModel.state.value,
                 event = {
                     viewModel.onEvent(it)
                 },
                 navigateToDetails = {article->
                     navigateToDetails(
                         navController,
                         article
                     )
                 }
             )
         }
         composable(route=Route.DetailsScreen.route){
             val viewModel: DetailsViewModel = hiltViewModel()
             //TODO: Handle side Effect

             if (viewModel.sideEffect !=null){
                 Toast.makeText(LocalContext.current,viewModel.sideEffect,Toast.LENGTH_SHORT).show()
                 viewModel.onEvent(DetailsEvent.RemoveSideEffect)
             }
             navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")?.let { article->
                 DetailsScreen(article = article, event =viewModel::onEvent, navigateUp = {navController.navigateUp()} )
             }
         }

         composable(route=Route.BookmarkScreen.route){
             val viewModel: BookmarkViewModel = hiltViewModel()
             BookmarkScreen(state = viewModel.state.value, navigateToDetails = {
                 navigateToDetails(navController, article = it)
             })
         }
     }
    }
}

private fun navigateToTop(navController: NavController,route:String){
    navController.navigate(route= route, builder = {
        navController.graph.startDestinationRoute?.let { homeScreen->
            popUpTo(route= homeScreen, popUpToBuilder = {
                saveState = true
            }
            )
            restoreState= true
            launchSingleTop= true
        }
    })
}

private fun navigateToDetails(navController: NavController,article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",`article`)
    navController.navigate(Route.DetailsScreen.route)
}