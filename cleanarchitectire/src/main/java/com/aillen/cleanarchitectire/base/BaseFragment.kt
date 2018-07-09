package com.aillen.cleanarchitectire.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aillen.cleanarchitectire.AndroidApplication
import com.aillen.cleanarchitectire.di.ApplicationComponent
import com.aillen.cleanarchitectire.di.ViewModelFactory
import com.aillen.cleanarchitectire.test.PresenterFactory
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

/**
 * Created by anlonglong on 2018/6/27.
 * Email： 940752944@qq.com
 */
abstract class BaseFragment : Fragment() {


    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    @Inject
    lateinit var factory: ViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    open fun onBackPressed() {}

    fun firstTimeCreate(savedInstanceState: Bundle?) = savedInstanceState == null

    fun showProgress() = progressStatus(View.VISIBLE)

    fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) = with(activity) {
        if (this is BaseActivity) {
            this.progress.visibility = viewStatus
        }
    }

    abstract fun layoutId(): Int
}