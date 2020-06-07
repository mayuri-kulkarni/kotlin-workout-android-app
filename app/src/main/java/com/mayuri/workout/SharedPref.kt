package com.mayuri.workout

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    val SHARED_PREF_NAME = "workout_app"
    init{
        preferences = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        editor = preferences.edit()
    }


    fun setUserId(UserId: String?) {
        editor!!.putString("UserId", UserId).commit()
    }
    fun getUserId(): String? {
        return preferences.getString("UserId","")
    }


}