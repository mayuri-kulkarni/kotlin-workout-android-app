package com.mayuri.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashboardListAdapter(private val list: List<String>, private val clickedItem : (Int) -> Unit)
    : RecyclerView.Adapter<DashboardListiewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardListiewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DashboardListiewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DashboardListiewHolder, position: Int) {
        val movie: String = list[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            clickedItem.invoke(position)
        }

    }

    override fun getItemCount(): Int = list.size

}

class DashboardListiewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_day_list, parent, false)) {
    private var mTitleView: TextView? = null
    private var view: View? = null


    init {
        mTitleView = itemView.findViewById(R.id.textview_date)
        view = itemView.findViewById(R.id.v_divider)
    }

    fun bind(data: String) {
        mTitleView?.text = data
    }


}