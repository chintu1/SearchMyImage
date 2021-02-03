package com.example.searchmyimage.data.repository

import com.example.searchmyimage.data.remote.apis.PixabayImageSearchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SearchImageRepository @Inject constructor(private val pixabayImageSearchApi: PixabayImageSearchApi) {

    suspend fun getImage(searchQuery:String)= pixabayImageSearchApi.getImage(searchQuery)
}
