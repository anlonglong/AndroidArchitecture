package com.aillen.cleanarchitectire.test

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by anlonglong on 2018/7/6.
 * Email： 940752944@qq.com
 */
@MapKey
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.PROPERTY_GETTER,AnnotationTarget.PROPERTY_SETTER)
annotation class PresenterKey(val value: KClass<out BasePresenter>)