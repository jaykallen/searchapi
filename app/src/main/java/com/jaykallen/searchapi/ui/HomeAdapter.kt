package com.jaykallen.searchapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaykallen.searchapi.R
import com.jaykallen.searchapi.model.Team
import kotlinx.android.synthetic.main.recycler_search.view.*

class HomeAdapter(
    private val itemList: List<Team>, var clickLambda: (Team) -> Unit
) : RecyclerView.Adapter<HomeAdapter.RecyclerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_search, parent, false)
        return RecyclerHolder(view)
    }

    override fun onBindViewHolder(recyclerholder: RecyclerHolder, position: Int) {
        val item: Team = itemList[position]
        recyclerholder.bindRecyclerHolder(item)
        recyclerholder.bindRecyclerData(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class RecyclerHolder(holder: View) : RecyclerView.ViewHolder(holder), View.OnClickListener {
        private lateinit var item: Team
        private val searchText: TextView = holder.searchText

        init {
            holder.setOnClickListener(this)
        }

        fun bindRecyclerHolder(item: Team) {
            this.item = item
        }

        fun bindRecyclerData(item: Team) {
            searchText.text = item.strTeam
        }

        override fun onClick(v: View) {
            clickLambda(item)
        }
    }
}
