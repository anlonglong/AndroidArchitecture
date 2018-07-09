package com.aillen.cleanarchitectire.annotation

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by anlonglong on 2018/6/29.
 * Email： 940752944@qq.com
 */
@MapKey
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.PROPERTY_GETTER,AnnotationTarget.PROPERTY_SETTER)
annotation class ViewModelKey(val value:KClass<out ViewModel>)