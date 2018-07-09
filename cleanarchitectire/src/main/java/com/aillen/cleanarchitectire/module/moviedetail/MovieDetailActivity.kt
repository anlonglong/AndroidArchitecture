package com.aillen.cleanarchitectire.module.moviedetail

import android.content.Context
import android.content.Intent
import com.aillen.cleanarchitectire.base.BaseActivity
import com.aillen.cleanarchitectire.base.BaseFragment
import com.aillen.cleanarchitectire.module.movies.MovieView

class MovieDetailActivity : BaseActivity() {

    companion object {

        private const val INTENT_EXTRA_PARAM_MOVIE = "com.aillen.cleanarchitectire.INTENT_PARAM_MOVIE"

        fun callingIntent(ctx:Context,movieView: MovieView): Intent{
            val intent = Intent(ctx, MovieDetailActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MOVIE,movieView)
            return intent
        }
    }

    override fun getFragment(): BaseFragment {
        return MovieDetailFragment.forMovie(intent.getParcelableExtra(INTENT_EXTRA_PARAM_MOVIE))
    }


}
