package com.dicoding.leagueoflegendchampion

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListChampionAdapter(private val listChampion: ArrayList<Champion>): RecyclerView.Adapter<ListChampionAdapter.ListviewHolder>() {
    class ListviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListviewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_champion, parent, false)
        return ListviewHolder(view)
    }

    override fun getItemCount(): Int = listChampion.size

    override fun onBindViewHolder(holder: ListviewHolder, position: Int) {
        val (name, _, _, _, _, photo) = listChampion[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name

        holder.itemView.setOnClickListener {
            val moveIntent = Intent(holder.itemView.context, DetailActivity::class.java)
            moveIntent.putExtra(DetailActivity.CHAMPIONS, listChampion[position])
            holder.itemView.context.startActivity(moveIntent)
        }
    }
}