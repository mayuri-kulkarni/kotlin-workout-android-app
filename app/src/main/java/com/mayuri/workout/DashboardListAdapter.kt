package com.mayuri.workout

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashboardListAdapter(private val list: List<String>, private val clickedItem : (Int) -> Unit)
    : RecyclerView.Adapter<DashboardListiewHolder>() {

    private var selectedItem  : Int = -1
    private var  oldItem  : Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardListiewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DashboardListiewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DashboardListiewHolder, position: Int) {
        val movie: String = list[position]
        holder.bind(movie)
        holder.unselect(position)
//        if( selectedItem!=-1 && oldItem!=-1 &&  oldItem==position)
//        holder.unselect(position)
        if( selectedItem!=-1 &&  selectedItem==position)
            holder.select(position)


        holder.itemView.setOnClickListener {
        oldItem = selectedItem
        notifyItemChanged(oldItem)
        clickedItem.invoke(position)
        selectedItem = position
        notifyItemChanged(selectedItem)

        }

    }

    override fun getItemCount(): Int = list.size

}
class DashboardListiewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_day_list, parent, false)) {
    var mTitleView: TextView? = null
     var view: View? = null

    init {
        mTitleView = itemView.findViewById(R.id.textview_date)
        view = itemView.findViewById(R.id.v_divider)
    }

    fun bind(data: String) {
        mTitleView?.text = data
    }
    fun unselect(unselectedItem: Int) {
        Log.d("TAG", "unselect: "+position+ view?.visibility)
        view?.visibility = View.GONE
        mTitleView?.textScaleX= 1f
        Log.d("TAG", "unselect: "+position+ view?.visibility)


    }
    fun select(selectedItem: Int) {
        Log.d("TAG", "select: "+position+ view?.visibility)
        mTitleView?.textScaleX= 1.1f
        view?.visibility = View.VISIBLE
        Log.d("TAG", "select: "+position+ view?.visibility)
    }


}