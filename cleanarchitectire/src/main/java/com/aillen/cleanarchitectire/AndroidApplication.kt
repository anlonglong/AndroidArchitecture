package com.aillen.cleanarchitectire

import android.app.Application
import com.aillen.cleanarchitectire.di.ApplicationComponent
import com.aillen.cleanarchitectire.di.ApplicationModule
import com.aillen.cleanarchitectire.di.DaggerApplicationComponent
import com.aillen.cleanarchitectire.net.LogUtil
import com.aillen.cleanarchitectire.net.http.anni.AnniInterceptor
import com.aillen.cleanarchitectire.net.http.anni.AnniRequest
import com.aillen.cleanarchitectire.net.http.anni.AnniResponse
import com.squareup.leakcanary.LeakCanary


/**
 * Created by anlonglong on 2018/6/27.
 * Email： 940752944@qq.com
 */
class AndroidApplication:Application() {

    val appComponent : ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        LogUtil.enable = BuildConfig.DEBUG
        AnniRequest.initContext(this)
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() {
        appComponent.inject(this)
    }

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}