package com.mayuri.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashboardExerciseListAdapter(private val list: MutableList<SingleExerciseData>)
    : RecyclerView.Adapter<DashboardExerciseListiewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardExerciseListiewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DashboardExerciseListiewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DashboardExerciseListiewHolder, position: Int) {
        val movie: SingleExerciseData = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}

class DashboardExerciseListiewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_exercise_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.textview_exercise_name)
        mYearView = itemView.findViewById(R.id.textview_exercise_details)
    }

    fun bind(data: SingleExerciseData) {
        mTitleView?.text = data.name
        mYearView?.text = data.count + " " +data.countUnit +" x " +data.sets +" sets"

    }

}