package com.cahyadesthian.peoplelit.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.peoplelit.R
import com.cahyadesthian.peoplelit.data.People
import org.w3c.dom.Text

class ListAdapter: RecyclerView.Adapter<ListAdapter.TheViewHolder>() {


    private var peopleList = emptyList<People>()

    inner class TheViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val idTv = itemView.findViewById<TextView>(R.id.people_id)
        val firtNameTv = itemView.findViewById<TextView>(R.id.tv_firstname)
        val lastNameTv = itemView.findViewById<TextView>(R.id.tv_lastname)
        val ageTv = itemView.findViewById<TextView>(R.id.tv_age)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.TheViewHolder {
        return TheViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.people_row,parent,false))
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        val currentItem = peopleList[position]
        //holder.itemView.findViewById<TextView>(R.id.people_id).text = currentItem.id.toString()
        holder.idTv.text = currentItem.id.toString()
        holder.firtNameTv.text = currentItem.firstName.toString()
        holder.lastNameTv.text = currentItem.lastName
        holder.ageTv.text = currentItem.age.toString()
    }

    override fun getItemCount(): Int = peopleList.size


    fun setData(peopleListDataSetUp: List<People>) {
        this.peopleList = peopleListDataSetUp
        notifyDataSetChanged()
    }


}