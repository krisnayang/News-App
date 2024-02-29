package com.example.articleapp.di

import android.content.Context
import com.example.articleapp.data.remote.api.service.APIService
import com.example.articleapp.repository.NewsRepository
import com.example.articleapp.repository.NewsRepositoryImpl
import com.example.articleapp.repository.SourcesRepository
import com.example.articleapp.repository.SourcesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
  @Provides
  @Singleton
  fun provideNewsRepository(
    api: APIService
  ): NewsRepository {
    return NewsRepositoryImpl(api)
  }

  @Provides
  @Singleton
  fun provideSourcesRepository(
    api: APIService
  ): SourcesRepository {
    return SourcesRepositoryImpl(api)
  }
}