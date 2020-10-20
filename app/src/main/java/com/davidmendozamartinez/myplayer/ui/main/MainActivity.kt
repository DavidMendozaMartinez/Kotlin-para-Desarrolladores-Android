package com.davidmendozamartinez.myplayer.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.davidmendozamartinez.myplayer.R
import com.davidmendozamartinez.myplayer.data.Filter
import com.davidmendozamartinez.myplayer.data.MediaItem
import com.davidmendozamartinez.myplayer.data.MediaItem.Type
import com.davidmendozamartinez.myplayer.data.MediaProvider
import com.davidmendozamartinez.myplayer.databinding.ActivityMainBinding
import com.davidmendozamartinez.myplayer.ui.detail.DetailActivity
import com.davidmendozamartinez.myplayer.ui.startActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = MediaAdapter {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
        updateItems()
    }

    private fun updateItems(filter: Filter = Filter.None) {
        lifecycleScope.launch {
            binding.progress.visibility = View.VISIBLE
            adapter.items = withContext(Dispatchers.IO) { getFilteredItems(filter) }
            binding.progress.visibility = View.GONE
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when (item.itemId) {
            R.id.filter_photos -> Filter.ByType(Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(Type.VIDEO)
            else -> Filter.None
        }

        updateItems(filter)
        return true
    }
}