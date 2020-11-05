package com.nikhil.slice.app

import android.app.Application
import android.util.Log
import com.nikhil.slice.BuildConfig
import timber.log.Timber

class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(BlankTree())
    }

    class BlankTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.ERROR) Timber.e(t)
        }
    }

}