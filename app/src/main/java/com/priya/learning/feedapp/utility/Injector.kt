package com.priya.learning.feedapp.utility

import androidx.fragment.app.Fragment
import com.priya.learning.feedapp.data.FeedRepository
import com.priya.learning.feedapp.viewModel.FeedViewModelFactory
import com.priya.learning.feedapp.api.ApiService


object Injector {
     fun getFeedViewModelFactory(fragment: Fragment):FeedViewModelFactory {
        return FeedViewModelFactory(FeedRepository(ApiService.create()))
    }

}