package com.mayuri.workout

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    val SHARED_PREF_NAME = "BigArt"
    init{
        preferences = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        editor = preferences.edit()
    }


    fun setUserEmail(UserEmail: String?) {
        editor!!.putString("User_Email", UserEmail).commit()
    }


}