package com.example.gameengine

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListGameAdapter(private val listGame: ArrayList<Game>): RecyclerView.Adapter<ListGameAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_item_desc)
        val tvDev = TextView(itemView.context)
        val tvDate = TextView(itemView.context)
        val btnSteam = TextView(itemView.context)
        val btnFeature = TextView(itemView.context)
        val imgThumb = ImageView(itemView.context)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_game, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listGame.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name,dev,date, steam, feature,thumb,photo, desc) = listGame[position]
        holder.imgPhoto.setImageResource(photo)
        holder.imgThumb.setImageResource(thumb)
        holder.tvName.text = name
        holder.tvDesc.text = desc
        holder.tvDev.text = dev
        holder.tvDate.text = date
        holder.btnSteam.text = steam
        holder.btnFeature.text = feature

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_games", listGame[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}