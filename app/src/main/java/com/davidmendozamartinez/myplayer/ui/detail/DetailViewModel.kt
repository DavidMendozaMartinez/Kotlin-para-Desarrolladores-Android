package com.davidmendozamartinez.myplayer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmendozamartinez.myplayer.data.MediaItem
import com.davidmendozamartinez.myplayer.data.MediaProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel : ViewModel() {

    private val _item = MutableLiveData<MediaItem>()
    val item: LiveData<MediaItem> get() = _item

    fun onCreate(itemId: Int) {
        viewModelScope.launch {
            val items = withContext(Dispatchers.IO) { MediaProvider.getItems() }
            val item = items.find { it.id == itemId }
            item?.let { _item.value = item }
        }
    }
}