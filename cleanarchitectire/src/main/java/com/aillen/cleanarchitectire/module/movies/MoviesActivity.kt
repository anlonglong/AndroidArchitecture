package com.aillen.cleanarchitectire.module.movies

import android.content.Context
import android.content.Intent
import com.aillen.cleanarchitectire.base.BaseActivity
import com.aillen.cleanarchitectire.base.BaseFragment

class MoviesActivity : BaseActivity() {
    override fun getFragment(): BaseFragment {
       return MovieFragment()
    }

    companion object {
        fun callingIntent(ctx:Context) = Intent(ctx, MoviesActivity::class.java)
    }

}
