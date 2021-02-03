package com.example.searchmyimage.utils

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.searchmyimage.R


fun ImageView.loadImage(context: Context, imageUrl: String) {
    Glide.with(context).load(imageUrl)
        .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.placeholdercolor)))
        .into(this)
}