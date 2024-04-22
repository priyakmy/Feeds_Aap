package com.priya.learning.feedapp.data

import androidx.paging.*
import com.priya.learning.feedapp.api.ApiService
import kotlinx.coroutines.flow.*

class FeedRepository(
    private val api: ApiService,
//    private val db:AppDataBase
) {

    fun getPostStream(): Flow<PagingData<Post>> {
         return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 5),
            initialKey = 1,
            pagingSourceFactory = { FeedPagingSource(api) }
        ).flow
    }
}