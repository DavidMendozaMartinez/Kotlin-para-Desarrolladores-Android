package com.davidmendozamartinez.myplayer.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.davidmendozamartinez.myplayer.R
import com.davidmendozamartinez.myplayer.data.Filter
import com.davidmendozamartinez.myplayer.data.MediaItem.Type
import com.davidmendozamartinez.myplayer.databinding.ActivityMainBinding
import com.davidmendozamartinez.myplayer.ui.detail.DetailActivity
import com.davidmendozamartinez.myplayer.ui.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = MediaAdapter { viewModel.onItemClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get<MainViewModel>().apply {
            items.observe(this@MainActivity, { adapter.items = it })
            progressVisible.observe(this@MainActivity, { binding.progress.isVisible = it })
            navigateToDetail.observe(this@MainActivity, { navigateToDetail(it) })
        }

        binding.recycler.adapter = adapter
        viewModel.onFilterSelected(Filter.None)
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

        viewModel.onFilterSelected(filter)
        return true
    }

    private fun navigateToDetail(id: Int) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to id)
    }
}