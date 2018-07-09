package com.aillen.cleanarchitectire.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.aillen.cleanarchitectire.exception.Failure

/**
 * Created by anlonglong on 2018/6/28.
 * Email： 940752944@qq.com
 *
 * 基类中包含的是全局的用来观察错误的Livedata，因为错误都封装在了[Failure]中，不管是什么错误发送的都是Failure类型，所以
 * 放在基类中同意处理
 * 而成功的数据类型是千变万化的，交给自己子类去实现。
 */
 open class BaseViewModel : ViewModel() {

    private val error = MutableLiveData<Failure>()

    private var _failures  = Transformations.switchMap(error){ getFailureLiveData(it) }

    val failure: LiveData<Failure>
        get() = _failures


    private fun getFailureLiveData(it: Failure?):LiveData<Failure> {
        val data = MutableLiveData<Failure>()
        data.value = it
        return data
    }

    protected fun handleFailure(failure: Failure) {
        this.error.value = failure
    }
}