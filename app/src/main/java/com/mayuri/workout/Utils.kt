package com.mayuri.workout

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    var dateFormatDB = "dd-MM-yyyy"
    var dateFormatUser = "EEE, dd MMM "

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }


    fun getTodayDate(format: String): String {
        val today = Calendar.getInstance().time
        val df = SimpleDateFormat(format)
        return df.format(today)
        var cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -1)

    }

    fun getPreviousDates(days :Int,format: String): String{
        val df = SimpleDateFormat(format)
        var cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -days)
        return df.format(cal.time)

    }
}