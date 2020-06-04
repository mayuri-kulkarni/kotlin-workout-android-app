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
import com.mayuri.workout.databinding.FragmentDashboardBinding
import com.mayuri.workout.databinding.FragmentTodaysExerciseBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class DashBoardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    lateinit var list: List<SingleExerciseData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // binding = FragmentAddExerciseBinding.inflate(inflater)
        var v = inflater.inflate(R.layout.fragment_dashboard, container, false)
        binding = FragmentDashboardBinding.bind(v)
        return v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
        setUpUi()

        DailyDataFirestore().getAllDayData {
            if(it){
                updateList();
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
        Fonts.setFonts(binding.root.rootView as ViewGroup)

        binding.recyclerviewExercise.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DashboardListAdapter(UtilsJava.allDays)

        }

    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }


}