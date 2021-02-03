package com.example.searchmyimage.di

import com.example.searchmyimage.data.remote.ApiBuilder
import com.example.searchmyimage.data.remote.apis.PixabayImageSearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkApiModule {

    @Provides
    @Singleton
    fun providesPixabayImageSearchApi(apiBuilder: ApiBuilder)=apiBuilder.buildService(PixabayImageSearchApi::class.java)
}