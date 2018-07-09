package com.aillen.cleanarchitectire.module.moviedetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_SHORT
import android.view.View
import com.aillen.cleanarchitectire.R
import com.aillen.cleanarchitectire.base.BaseFragment
import com.aillen.cleanarchitectire.core.extension.*
import com.aillen.cleanarchitectire.exception.Failure
import com.aillen.cleanarchitectire.module.movies.MovieView
import com.aillen.cleanarchitectire.test.Factory
import com.aillen.cleanarchitectire.test.MyPresenter
import com.aillen.cleanarchitectire.test.PresenterFactory
import com.aillen.cleanarchitectire.test.PresenterProviders
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 */

/**NOTE:
 * 在做转场动画的时候，如果两个页面之间需要共享某一个元素，则这两个页面的配色要差不多，这样在开启新界面传递
 * 共享元素的时候不会出现不和谐的视觉差，
 * 必去：A页面的全局色是蓝色
 *      B页面的全局色是白色
 *      在A中点击条目去B的时候，共享元素会显示短暂的A的全局色而造成短暂的不和谐视差，
 *      解决办法就是A页面和B页有着一样的全局色，这样做A页面在跳转到的时候也有短暂的全局色，但是由于A和B页面的
 *      全局色一样，而看不出来.
 * **/
class MovieDetailFragment : BaseFragment() {


    companion object {
        private const val PARAM_MOVIE = "param_movie"
        fun forMovie(movieView: MovieView) = with(MovieDetailFragment()) {
            val bundle = Bundle()
            bundle.putParcelable(PARAM_MOVIE, movieView)
            this.arguments = bundle
            this
        }
    }

    private lateinit var viewModule: MovieDetailViewModule

    private lateinit var presenter: MyPresenter

    @Inject
    lateinit var animator: MovieDetailsAnimator

    @Inject lateinit var presenterFactor: PresenterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        presenter = PresenterProviders.of(presenterFactor, MyPresenter::class.java)
        presenter.p()
        viewModule = viewModule(factory) {
            observer(movieDetails, ::handlerMovieDetail)
            failure(failure, ::handleFailure)
        }
    }

    private fun handleFailure(failure: Failure?) {
        hideProgress()
        println("failure = [${failure.toString()}]")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieView: MovieView = arguments!!.getParcelable(PARAM_MOVIE)
        if (firstTimeCreate(savedInstanceState)) {
            viewModule.loadMovieDetails(movieView.id)
        } else {
            animator.scaleUpView(moviePlay)
            animator.cancelTransition(moviePlay)
            moviePoster.loadFromUrl(movieView.poster)
        }

        moviePlay.setOnClickListener { Snackbar.make(moviePoster,"播放。。。。。。",LENGTH_SHORT).show()}

    }

    override fun onBackPressed() {
         animator.fadeInvisible(scrollView,movieDetails)
    }

    private fun handlerMovieDetail(movie: MovieDetails?) {
        hideProgress()
        println("movieDetails = [$movieDetails]")
        activity?.let {
           postponeEnterTransition()
        }
        moviePoster.loadUrlAndPostponeEnterTransition(movie!!.poster, activity!!)

        activity!!.toolbar.title = movie.title
        with(movie) {
            movieSummary.text = summary
            movieCast.text = cast
            movieDirector.text = director
            movieYear.text = year.toString()
        }
        animator.apply {
            fadeVisible(scrollView, movieDetails)
            scaleUpView(moviePlay)
        }
    }

    override fun layoutId() = R.layout.fragment_movie_detail
}


