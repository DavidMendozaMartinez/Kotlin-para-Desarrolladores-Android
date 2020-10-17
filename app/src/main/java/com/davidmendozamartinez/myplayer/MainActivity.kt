package com.davidmendozamartinez.myplayer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.davidmendozamartinez.myplayer.databinding.ActivityMainBinding
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

    private fun updateItems(filterId: Int = R.id.filter_all) {
        lifecycleScope.launch {
            binding.progress.visibility = View.VISIBLE
            adapter.items = withContext(Dispatchers.IO) { getFilteredItems(filterId) }
            binding.progress.visibility = View.GONE
        }
    }

    private fun getFilteredItems(filterId: Int): List<MediaItem> {
        return MediaProvider.getItems().let { media ->
            when (filterId) {
                R.id.filter_all -> media
                R.id.filter_photos -> media.filter { it.type == MediaItem.Type.PHOTO }
                R.id.filter_videos -> media.filter { it.type == MediaItem.Type.VIDEO }
                else -> emptyList()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        updateItems(item.itemId)
        return true
    }
}