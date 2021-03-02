package com.ab.useralbum.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ab.useralbum.R

/**
 * Created by Aya Boussaadia on 02,March,2021
 */


class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var count: Int = 0

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val userNumber: TextView = itemView.findViewById(R.id.user_number_text)

        fun bind(usersnumber: Int) {
            userNumber.text = usersnumber.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(count)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateUserNumber(countUser: Int) {
        count = countUser
        notifyDataSetChanged()
    }
}