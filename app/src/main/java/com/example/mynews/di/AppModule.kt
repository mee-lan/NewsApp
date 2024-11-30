package com.example.mynews.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynews.data.local.NewsDao
import com.example.mynews.data.local.NewsDatabase
import com.example.mynews.data.local.NewsTypeConverter
import com.example.mynews.data.manager.LocalUserManagerImpl
import com.example.mynews.data.remote.NewsApi
import com.example.mynews.data.respository.NewsRepositoryImpl
import com.example.mynews.domain.manager.LocalUserManager
import com.example.mynews.domain.manager.usecases.app_entry.AppEntryUseCases
import com.example.mynews.domain.manager.usecases.app_entry.ReadAppEntry
import com.example.mynews.domain.manager.usecases.app_entry.SaveAppEntry
import com.example.mynews.domain.manager.usecases.news.DeleteArticle
import com.example.mynews.domain.manager.usecases.news.GetNews
import com.example.mynews.domain.manager.usecases.news.NewsUseCases
import com.example.mynews.domain.manager.usecases.news.SearchNews
import com.example.mynews.domain.manager.usecases.news.SelectArticle
import com.example.mynews.domain.manager.usecases.news.SelectArticles
import com.example.mynews.domain.manager.usecases.news.UpsertArticle
import com.example.mynews.domain.respository.NewsRepository
import com.example.mynews.util.Constants
import com.example.mynews.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository = newsRepository),
            searchNews = SearchNews(newsRepository=newsRepository),
            selectArticles = SelectArticles(newsDao = newsDao),
            upsertArticle = UpsertArticle(newsDao=newsDao),
            deleteArticle = DeleteArticle(newsDao= newsDao),
            selectArticle = SelectArticle(newsDao=newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Constants.NEWS_DATABASE_NAME)
            .addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao{
        return newsDatabase.newsDao
    }

}