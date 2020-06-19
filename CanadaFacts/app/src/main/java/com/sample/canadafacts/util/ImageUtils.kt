package com.sample.canadafacts.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions

object ImageUtils {
    /**
     * set image using Glide
     * @param imagePath : image url
     * @param imageView : image view on which image is to be set
     * @param context : Context
     */
    fun setFactsImage(
        imagePath: String?,
        imageView: ImageView,
        context: Context
    ) {
        Glide.with(context).load(imagePath)
            .apply(
                RequestOptions().centerCrop()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
            ).listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.VISIBLE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.visibility = View.GONE
                    return false
                }
            }).into(imageView)
    }
}
