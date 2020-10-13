package com.davidmendozamartinez.myplayer

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidmendozamartinez.myplayer.databinding.ViewMediaItemBinding

class MediaAdapter(private val items: List<MediaItem>, private val listener: (MediaItem) -> Unit) :
        RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_media_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ViewMediaItemBinding.bind(view)

        fun bind(mediaItem: MediaItem) = with(binding) {
            mediaTitle.text = mediaItem.title
            mediaThumb.loadUrl(mediaItem.url)
            mediaVideoIndicator.visibility = when (mediaItem.type) {
                MediaItem.Type.PHOTO -> View.GONE
                MediaItem.Type.VIDEO -> View.VISIBLE
            }
        }
    }
}