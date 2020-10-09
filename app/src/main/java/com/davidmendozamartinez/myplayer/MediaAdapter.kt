package com.davidmendozamartinez.myplayer

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MediaAdapter(private val items: List<MediaItem>) : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_media_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.mediaTitle)
        private val thumb: ImageView = view.findViewById(R.id.mediaThumb)

        fun bind(mediaItem: MediaItem) {
            title.text = mediaItem.title
            Glide.with(thumb).load(mediaItem.url).into(thumb)

            itemView.setOnClickListener { toast(mediaItem.title) }
        }
    }
}