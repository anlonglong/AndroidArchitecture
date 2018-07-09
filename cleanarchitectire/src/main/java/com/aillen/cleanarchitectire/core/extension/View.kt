package com.aillen.cleanarchitectire.core.extension

import android.graphics.drawable.Drawable
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.BaseTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

/**
 * Created by anlonglong on 2018/6/29.
 * Email： 940752944@qq.com
 */
fun View.visible() {visibility = View.VISIBLE}

fun View.inVisible(){visibility = View.GONE}

fun View.isVisible() = visibility == View.VISIBLE

fun View.inflate(layoutId:Int) = View.inflate(this.context,layoutId,null)

fun ImageView.loadFromUrl(url:String){
    Glide
    .with(this.context.applicationContext).load(url)
    .transition(DrawableTransitionOptions.withCrossFade())
    .into(this)
}

fun ImageView.loadUrlAndPostponeEnterTransition(url: String, activity: FragmentActivity) {
    val target: Target<Drawable> = ImageViewBaseTarget(this,
            activity)
    Glide.with(context.applicationContext).load(url).into(target)
}

fun View.cancelTransition() {
    transitionName = null
}


private class ImageViewBaseTarget (var imageView: ImageView?, var activity: FragmentActivity?) : BaseTarget<Drawable>() {
    override fun removeCallback(cb: SizeReadyCallback?) {
        imageView = null
        activity = null
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>) {
        imageView?.setImageDrawable(resource)
        activity?.supportStartPostponedEnterTransition()
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)
        activity?.supportStartPostponedEnterTransition()
    }

    override fun getSize(cb: SizeReadyCallback) = cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)
}
