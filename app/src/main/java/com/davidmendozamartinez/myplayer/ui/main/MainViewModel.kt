package com.davidmendozamartinez.myplayer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmendozamartinez.myplayer.data.Filter
import com.davidmendozamartinez.myplayer.data.MediaItem
import com.davidmendozamartinez.myplayer.data.MediaProvider
import com.davidmendozamartinez.myplayer.ui.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _items = MutableLiveData<List<MediaItem>>()
    val items: LiveData<List<MediaItem>> get() = _items

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _navigateToDetail = MutableLiveData<Event<Int>>()
    val navigateToDetail: LiveData<Event<Int>> get() = _navigateToDetail

    fun onFilterSelected(filter: Filter) {
        viewModelScope.launch {
            _progressVisible.value = true
            _items.value = withContext(Dispatchers.IO) { getFilteredItems(filter) }
            _progressVisible.value = false
        }
    }

    private fun getFilteredItems(filter: Filter): List<MediaItem> {
        return MediaProvider.getItems().let { media ->
            when (filter) {
                Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }
    }

    fun onItemClicked(item: MediaItem) {
        _navigateToDetail.value = Event(item.id)
    }
}