package com.davidmendozamartinez.myplayer.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.davidmendozamartinez.myplayer.data.MediaItem.Type
import com.davidmendozamartinez.myplayer.databinding.ActivityDetailBinding
import com.davidmendozamartinez.myplayer.ui.loadUrl

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "DetailActivity:extraId"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get<DetailViewModel>().apply {
            item.observe(this@DetailActivity, {
                supportActionBar?.title = it.title
                binding.detailThumb.loadUrl(it.url)
                binding.detailVideoIndicator.isVisible = it.type == Type.VIDEO
            })
        }

        viewModel.onCreate(intent.getIntExtra(EXTRA_ID, -1))
    }
}