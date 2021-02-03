package com.example.searchmyimage.data.model

import com.google.gson.annotations.SerializedName

data class PixaBayApiResponse(
    @SerializedName("hits")
    val pixabaySearchResults: List<PixabaySearchResult>,
    val total: Int,
    val totalHits: Int
)