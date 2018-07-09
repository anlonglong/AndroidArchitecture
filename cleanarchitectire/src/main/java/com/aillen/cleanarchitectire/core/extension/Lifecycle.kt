package com.aillen.cleanarchitectire.core.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.aillen.cleanarchitectire.exception.Failure

/**
 * Created by anlonglong on 2018/6/29.
 * Email： 940752944@qq.com
 *
 * 注册LiveData的观察者
 */

//成功的注册
fun <T :Any,L : LiveData<T>> LifecycleOwner.observer(liveData:L,body:(T?) ->Unit){
    liveData.observe(this, Observer<T> { t -> body(t) })
}

//失败的注册
fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData:L,body: (Failure?) -> Unit){
    liveData.observe(this, Observer { body(it) })
}