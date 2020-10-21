package com.davidmendozamartinez.myplayer.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.davidmendozamartinez.myplayer.R
import com.davidmendozamartinez.myplayer.data.Filter
import com.davidmendozamartinez.myplayer.data.MediaItem
import com.davidmendozamartinez.myplayer.data.MediaItem.Type
import com.davidmendozamartinez.myplayer.databinding.ActivityMainBinding
import com.davidmendozamartinez.myplayer.ui.detail.DetailActivity
import com.davidmendozamartinez.myplayer.ui.startActivity

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this, lifecycleScope)
    private val adapter = MediaAdapter { presenter.onItemClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
        presenter.onFilterSelected(Filter.None)
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

        presenter.onFilterSelected(filter)
        return true
    }

    override fun setProgressVisible(visible: Boolean) {
        binding.progress.isVisible = visible
    }

    override fun updateItems(items: List<MediaItem>) {
        adapter.items = items
    }

    override fun navigateToDetail(id: Int) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to id)
    }
}