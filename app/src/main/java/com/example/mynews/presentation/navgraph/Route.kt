package com.example.mynews.presentation.navgraph

sealed class Route(
    val route:String
) {
    object OnboardingScreen :Route("onBoardingScreen")
    object HomeScreen:Route("homeScreen")
    object SearchScreen:Route("searchScreen")
    object BookmarkScreen:Route("bookmarkScreen")
    object DetailsScreen :Route("detailsScreen")
    object AppStartNavigation :Route("appStartNavigation")
    object NewsNavigation :Route("newsNavigation")
    object NewsNavigatorScreen :Route("newsNavigatorScreen")
}
