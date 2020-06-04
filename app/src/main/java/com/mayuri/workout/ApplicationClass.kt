package com.mayuri.workout;

import android.app.Application;
import timber.log.Timber

class ApplicationClass : Application() {


     override fun onCreate() {
         super.onCreate()
         Timber.plant(Timber.DebugTree())
         Fonts.initFonts(applicationContext)


     }
}
