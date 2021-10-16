package com.example.common

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()

            ARouter.openLog()
            ARouter.openDebug()

        ARouter.init(this)
    }

}