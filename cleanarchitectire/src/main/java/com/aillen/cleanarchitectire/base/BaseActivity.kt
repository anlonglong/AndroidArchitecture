package com.aillen.cleanarchitectire.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.aillen.cleanarchitectire.R
import com.aillen.cleanarchitectire.core.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        setSupportActionBar(toolbar)
        with(supportActionBar){
            title = "Movie"
        }

        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) {
        savedInstanceState?:supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer,getFragment())
        }
        println("fragments size = ${supportFragmentManager.fragments.size}")
    }

    abstract fun getFragment(): BaseFragment
}
