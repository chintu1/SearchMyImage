package com.example.searchmyimage.ui.searchimage.diffutills

import androidx.recyclerview.widget.DiffUtil
import com.example.searchmyimage.data.model.PixabaySearchResult
import javax.inject.Inject

class SearchImageDiffUtil : DiffUtil.ItemCallback<PixabaySearchResult>() {

    override fun areItemsTheSame(
        oldItem: PixabaySearchResult,
        newItem: PixabaySearchResult
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PixabaySearchResult,
        newItem: PixabaySearchResult
    ): Boolean {
        return oldItem==newItem
    }

}