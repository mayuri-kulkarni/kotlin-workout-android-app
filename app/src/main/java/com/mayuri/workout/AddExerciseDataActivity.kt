package com.mayuri.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mayuri.workout.UtilsJava.currentDay
import com.mayuri.workout.databinding.ActivityAddExerciseDataBinding
import timber.log.Timber


class AddExerciseDataActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddExerciseDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseDataBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add_exercise_data)
        currentDay = ArrayList()
        //ADD FRAGMENT using inline function
        supportFragmentManager.inTransaction {
            add(binding.mainFragment.id, TodaysExerciseFragment())
        }

    }


    public fun dateSelected(){

    }

}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}
