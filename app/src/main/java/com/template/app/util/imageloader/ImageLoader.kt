package com.template.app.util.imageloader

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.template.app.util.display.DisplayMetrics

/**
 * This class is used for loading images from server and from drawable resource.
 * It will be injected by the DI framework.
 */
class ImageLoader(val context: Context) {

    enum class Transformation(val multiTransformation: MultiTransformation<Bitmap>) {
        CENTER_CROP(
                MultiTransformation(
                        CenterCrop()
                )
        ),
        RADIUS_10(
                MultiTransformation(
                        RoundedCorners(DisplayMetrics.convertDpToPixel(10F).toInt())
                )
        ),
        CENTER_CROP_WITH_RADIUS_8(
                MultiTransformation(
                        CenterCrop(),
                        RoundedCorners(DisplayMetrics.convertDpToPixel(8F).toInt())
                )
        ),
        CENTER_CROP_WITH_RADIUS_100(
                MultiTransformation(
                        CenterCrop(),
                        RoundedCorners(DisplayMetrics.convertDpToPixel(100F).toInt())
                )
        ),
        CENTER_CROP_WITH_RADIUS_50(
                MultiTransformation(
                        CenterCrop(),
                        RoundedCorners(DisplayMetrics.convertDpToPixel(50F).toInt())
                )
        )/*,
        CENTER_CROP_WITH_MASKING(
                MultiTransformation(
                        CenterCrop(),
                        MaskTransformation(R.drawable.image_ranking_mask)
                )
        )*/
    }

    /**
     *
     */
    fun loadImage(
            path: String,
            imageView: AppCompatImageView,
            transformation: Transformation? = null
    ) {
        var glideRequest = GlideApp.with(context)
                .load(path)

        if (transformation != null) {
            glideRequest = glideRequest.apply(RequestOptions.bitmapTransform(transformation.multiTransformation))

        }

        glideRequest.into(imageView)
    }

    /**
     *
     */
    fun loadImage(
            @DrawableRes resId: Int,
            imageView: AppCompatImageView,
            transformation: Transformation? = null
    ) {
        var glideRequest = GlideApp.with(context)
                .load(ContextCompat.getDrawable(context, resId))

        if (transformation != null) {
            glideRequest = glideRequest.apply(RequestOptions.bitmapTransform(transformation.multiTransformation))

        }

        glideRequest.into(imageView)
    }

    /**
     *
     */
    fun loadGIF(@DrawableRes resId: Int, imageView: AppCompatImageView) {
        GlideApp.with(context)
                .load(resId)
                .into(imageView)
    }
}