package com.aillen.cleanarchitectire.module.movies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aillen.cleanarchitectire.AndroidApplication
import com.aillen.cleanarchitectire.di.ApplicationComponent
import com.aillen.cleanarchitectire.navigation.Navigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component:ApplicationComponent by lazy (mode = LazyThreadSafetyMode.NONE){
        (application as AndroidApplication).appComponent
    }

    @Inject lateinit var navigator:Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        navigator.show(this)
        finish()
    }
}
