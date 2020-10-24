package com.davidmendozamartinez.myplayer.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.davidmendozamartinez.myplayer.CoroutinesTestRule
import com.davidmendozamartinez.myplayer.data.FakeMediaProvider
import com.davidmendozamartinez.myplayer.data.Filter
import com.davidmendozamartinez.myplayer.data.MediaItem
import com.davidmendozamartinez.myplayer.data.MediaItem.Type
import com.davidmendozamartinez.myplayer.ui.Event
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: MainViewModel
    private val fakeMediaProvider = FakeMediaProvider()

    @Before
    fun setUp() {
        viewModel = MainViewModel(fakeMediaProvider, coroutinesTestRule.testDispatcher)
    }

    @Test
    fun `progress is set visible when onFilterSelected`() =
            coroutinesTestRule.testDispatcher.runBlockingTest {
                val observer = mock<Observer<Boolean>>()
                viewModel.progressVisible.observeForever(observer)

                viewModel.onFilterSelected(Filter.None)

                verify(observer).onChanged(true)
            }

    @Test
    fun `updates items when onFilterSelected`() =
            coroutinesTestRule.testDispatcher.runBlockingTest {
                val observer = mock<Observer<List<MediaItem>>>()
                viewModel.items.observeForever(observer)

                viewModel.onFilterSelected(Filter.None)

                verify(observer).onChanged(fakeMediaProvider.getItems())
            }

    @Test
    fun `navigates to detail when onItemClicked`() =
            coroutinesTestRule.testDispatcher.runBlockingTest {
                val observer = mock<Observer<Event<Int>>>()
                viewModel.navigateToDetail.observeForever(observer)

                val mediaItem = MediaItem(1, "", "", Type.PHOTO)
                viewModel.onItemClicked(mediaItem)

                verify(observer).onChanged(Event(1))
            }
}