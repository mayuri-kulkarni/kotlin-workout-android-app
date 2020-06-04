package com.mayuri.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayuri.workout.databinding.FragmentAddExerciseBinding
import com.mayuri.workout.databinding.FragmentTodaysExerciseBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class TodaysExerciseFragment : Fragment() {
    lateinit var binding: FragmentTodaysExerciseBinding
    lateinit var list: List<SingleExerciseData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // binding = FragmentAddExerciseBinding.inflate(inflater)
        var v = inflater.inflate(R.layout.fragment_todays_exercise, container, false)
        binding = FragmentTodaysExerciseBinding.bind(v)
        return v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
        setUpUi()

        DailyDataFirestore().updateTodays {
            if(it){
                updateList();
            }
        }


        binding.textviewAddExercise.setOnClickListener {
            fragmentManager?.inTransaction {
                var fr = fragmentManager!!.fragments
                if( fragmentManager!!.fragments.isNotEmpty() && fragmentManager!!.fragments[0].id!=-1  )
                this.replace(fragmentManager!!.fragments[0].id,AddExerciseFragment(), "").addToBackStack(null)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        updateList();


    }

    private fun updateList() {
        binding.recyclerviewExercise.apply {
            adapter?.notifyDataSetChanged()
        }    }

    private fun setUpUi() {
        binding.date.text = Utils().getTodayDate(Utils().dateFormatUser)
        Fonts.setFonts(binding.root.rootView as ViewGroup)

        binding.recyclerviewExercise.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ExerciseListAdapter(UtilsJava.currentDay)

        }

    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }


}