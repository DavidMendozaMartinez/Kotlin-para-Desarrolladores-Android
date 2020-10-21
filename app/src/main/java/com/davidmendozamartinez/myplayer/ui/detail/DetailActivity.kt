package com.davidmendozamartinez.myplayer.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.davidmendozamartinez.myplayer.databinding.ActivityDetailBinding
import com.davidmendozamartinez.myplayer.ui.loadUrl

class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    companion object {
        const val EXTRA_ID = "DetailActivity:extraId"
    }

    private lateinit var binding: ActivityDetailBinding
    private val presenter = DetailPresenter(this, lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onCreate(intent.getIntExtra(EXTRA_ID, -1))
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setImage(url: String) {
        binding.detailThumb.loadUrl(url)
    }

    override fun setVideoIndicatorVisible(visible: Boolean) {
        binding.detailVideoIndicator.isVisible = visible
    }
}