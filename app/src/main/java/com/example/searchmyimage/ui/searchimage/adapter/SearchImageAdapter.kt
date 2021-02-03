package com.example.searchmyimage.ui.searchimage.adapter

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchmyimage.R
import com.example.searchmyimage.data.model.PixabaySearchResult
import com.example.searchmyimage.databinding.SearchImageRowItemLayoutBinding
import com.example.searchmyimage.ui.searchimage.diffutills.SearchImageDiffUtil
import com.example.searchmyimage.utils.loadImage
import javax.inject.Inject

class SearchImageAdapter @Inject constructor() :
    RecyclerView.Adapter<SearchImageAdapter.SearchImageItemViewHolder>() {

    lateinit var context: Context
    private lateinit var searchImageRowItemLayoutBinding: SearchImageRowItemLayoutBinding
    private val mDiffer = AsyncListDiffer(this, SearchImageDiffUtil())


    inner class SearchImageItemViewHolder(val searchImageRowItemLayoutBinding: SearchImageRowItemLayoutBinding) :
        RecyclerView.ViewHolder(searchImageRowItemLayoutBinding.root) {
        fun bind(pixabaySearchResult: PixabaySearchResult) {
            pixabaySearchResult.apply {
                (searchImageRowItemLayoutBinding.searchImageImageview.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio =
                    "$imageWidth:$imageHeight"
                searchImageRowItemLayoutBinding.searchImageImageview.loadImage(context, previewURL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageItemViewHolder {
        searchImageRowItemLayoutBinding =
            SearchImageRowItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return SearchImageItemViewHolder(searchImageRowItemLayoutBinding)
    }

    override fun onBindViewHolder(holder: SearchImageItemViewHolder, position: Int) {
        holder.bind(mDiffer.currentList.get(position))
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    public fun setData(searchImageResultList: List<PixabaySearchResult>) {
        mDiffer.submitList(searchImageResultList)
    }
}