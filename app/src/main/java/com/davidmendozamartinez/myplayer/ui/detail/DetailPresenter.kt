package com.davidmendozamartinez.myplayer.ui.detail

import com.davidmendozamartinez.myplayer.data.MediaItem.Type
import com.davidmendozamartinez.myplayer.data.MediaProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPresenter(private val view: View, private val scope: CoroutineScope) {

    interface View {
        fun setTitle(title: String)
        fun setImage(url: String)
        fun setVideoIndicatorVisible(visible: Boolean)
    }

    fun onCreate(itemId: Int) {
        scope.launch {
            val items = withContext(Dispatchers.IO) { MediaProvider.getItems() }
            val item = items.find { it.id == itemId }

            item?.let {
                view.setTitle(item.title)
                view.setImage(item.url)
                view.setVideoIndicatorVisible(item.type == Type.VIDEO)
            }
        }
    }
}