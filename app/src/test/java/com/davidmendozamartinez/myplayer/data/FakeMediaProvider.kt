package com.davidmendozamartinez.myplayer.data

class FakeMediaProvider : MediaProvider {
    override fun getItems(): List<MediaItem> = emptyList()
}