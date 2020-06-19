package com.sample.canadafacts.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sample.canadafacts.R

object ImageUtils {
    fun setFactsImage(
        imagePath: String?,
        imageView: ImageView,
        context: Context
    ) {
        Glide.with(context).load(imagePath)
            .apply(
                RequestOptions().centerCrop().error(R.drawable.ic_launcher_background)
                    .fitCenter().placeholder(R.drawable.ic_launcher_background)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
            ).into(imageView)
    }
}
