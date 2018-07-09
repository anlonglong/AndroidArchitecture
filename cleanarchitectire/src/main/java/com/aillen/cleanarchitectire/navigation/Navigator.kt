package com.aillen.cleanarchitectire.navigation

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import com.aillen.cleanarchitectire.module.moviedetail.MovieDetailActivity
import com.aillen.cleanarchitectire.module.movies.MovieView
import com.aillen.cleanarchitectire.module.movies.MoviesActivity
import javax.inject.Inject

/**
 * Created by anlonglong on 2018/6/27.
 * Email： 940752944@qq.com
 *
 * desc:
 * 页面跳转的时候要经过这个类，如有登录或者未登录的区分的，可以在这里引入[Authentication]这个类，做判断
 */

class Navigator @Inject constructor() {
    fun show(ctx:Context) {
        ctx.startActivity(MoviesActivity.callingIntent(ctx))
    }

    fun toMovieDetail(ctx: Context,movieView: MovieView){
        ctx.startActivity(MovieDetailActivity.callingIntent(ctx,movieView))
    }

    fun showMovieDetail(activity: FragmentActivity,moviewView: MovieView,extras: Extras){
        val intent = MovieDetailActivity.callingIntent(activity, moviewView)
        val shareView = extras.transitionSharedElement as ImageView
        val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, shareView, shareView.transitionName)
        activity.startActivity(intent,activityOptionsCompat.toBundle())
    }

}

class Extras(val transitionSharedElement:View)