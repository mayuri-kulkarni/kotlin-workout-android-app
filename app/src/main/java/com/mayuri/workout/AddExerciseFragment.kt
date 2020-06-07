package com.mayuri.workout

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.mayuri.workout.databinding.FragmentAddExerciseBinding

/**
 * A simple [Fragment] subclass.
 */
class AddExerciseFragment : Fragment() {
    lateinit var binding: FragmentAddExerciseBinding
    lateinit var list: List<SingleExerciseData>
    var color: Int = 0
    var transparent: Int = 0
    var colorBack: Int = 0
    var colorUnselectedText: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // binding = FragmentAddExerciseBinding.inflate(inflater)
        var v = inflater.inflate(R.layout.fragment_add_exercise, container, false)
        binding = FragmentAddExerciseBinding.bind(v)
        return v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        color = resources.getColor(R.color.gray)
        colorBack = resources.getColor(R.color.black_light)
        colorUnselectedText = resources.getColor(R.color.unselected_text)
        transparent = resources.getColor(R.color.transparent)


        setUpUi()
        clickEventSetUp()

    }

    fun updateData() {
        var dataAd = DailyDataFirestore();

        var countUnit = if (binding.reps.isClickable)
            dataAd.countUnitFormatReps
        else
            dataAd.countUnitFormatDuration
        var count= if (binding.reps.isClickable)
            binding.reps.text.toString()
        else
            binding.duration.text.toString()
        var data =SingleExerciseData(binding.etExercise.text.toString(),
            countUnit,
            count ,
            binding.etSets.text.toString())
        dataAd.add(
            context?.let { SharedPref(it).getUserId() }!!,
           data ){
            if(it){
                fragmentManager!!.popBackStack()
            }else{

            }
        }


    }

    private fun clickEventSetUp() {

        binding.buttonAddExercise.setOnClickListener {
            if (binding.etExercise.text.toString().isNotEmpty()) {
                updateData()
            }
        }

        binding.etExercise.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (p0.isEmpty()) {
                    binding.etExercise.background.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    binding.etExercise.setCompoundDrawablesWithIntrinsicBounds(
                        getDrawable(R.drawable.ic_search),
                        null, null, null
                    )
                } else {
                    binding.etExercise.background.setColorFilter(
                        transparent,
                        PorterDuff.Mode.SRC_IN
                    );
                    binding.etExercise.setCompoundDrawablesWithIntrinsicBounds(
                        getDrawable(R.drawable.ic_search),
                        null, null, null
                    )
                    binding.etExercise.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)


                }
            }

        })

        binding.llDurationSection.setOnClickListener {
            disableRepsSection()
            selectDuration()
        }
        binding.llRepsSection.setOnClickListener {
            disableDuration()
            selectReps()
        }

    }

    private fun selectReps() {
        binding.reps.isClickable = true

        binding.reps.setTextColor(colorBack)
        binding.textviewReps.setTextColor(colorBack)
        binding.imageviewReps.setImageDrawable(getDrawable(R.drawable.ic_radio_selected))


    }

    private fun selectDuration() {
        binding.duration.isClickable = true
        binding.duration.setTextColor(colorBack)
        binding.textviewDuration.setTextColor(colorBack)
        binding.imageviewDuration.setImageDrawable(getDrawable(R.drawable.ic_radio_selected))


    }

    private fun disableDuration() {
        binding.duration.isClickable = false
        binding.duration.setTextColor(color)
        binding.textviewDuration.setTextColor(colorUnselectedText)
        binding.imageviewDuration.setImageDrawable(getDrawable(R.drawable.radio_not_selected))


    }

    private fun disableRepsSection() {
        binding.reps.isClickable = false
        binding.reps.setTextColor(color)
        binding.textviewReps.setTextColor(colorUnselectedText)
        binding.imageviewReps.setImageDrawable(getDrawable(R.drawable.radio_not_selected))
    }

    private fun setUpUi() {
        Fonts.setFonts(binding.root.rootView as ViewGroup)
        binding.etExercise.background.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        binding.etExercise.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;

        selectDuration()
        disableRepsSection()

    }


    private fun getDrawable(drawable: Int): Drawable {
        return resources.getDrawable(drawable)
    }
}