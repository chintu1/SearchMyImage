package com.example.searchmyimage.data.remote.apis

import com.example.searchmyimage.data.model.PixaBayApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayImageSearchApi {

    @GET(".")
    suspend fun getImage(@Query("q") searchQuery:String):PixaBayApiResponse
}