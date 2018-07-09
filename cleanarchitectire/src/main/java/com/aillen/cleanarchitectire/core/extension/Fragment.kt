package com.aillen.cleanarchitectire.core.extension

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.aillen.cleanarchitectire.base.BaseActivity
import com.aillen.cleanarchitectire.base.BaseFragment
import kotlinx.android.synthetic.main.activity_layout.*

/**
 * Created by anlonglong on 2018/6/27.
 * Email： 940752944@qq.com
 */
internal inline fun FragmentManager.inTransaction(block:FragmentTransaction.() ->FragmentTransaction){
    this.beginTransaction().block().commitNowAllowingStateLoss()
}

inline fun <reified V : ViewModel>BaseFragment.viewModule(factory: ViewModelProvider.Factory,body:V.() ->Unit):V{
    val v = ViewModelProviders.of(this, factory)[V::class.java]
    v.body()
    return v
}

fun BaseFragment.close() = fragmentManager?.popBackStack()

fun BaseFragment.appContext() = this.context!!

val BaseFragment.viewContainer: View get() = (this.activity as BaseActivity).fragmentContainer