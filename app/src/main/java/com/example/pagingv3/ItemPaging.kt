package com.example.pagingv3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.IllegalStateException


class ItemPaging : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response =
                RetrofitClient.apiService.getData(nextPage).body() ?: return LoadResult.Error(
                    IllegalStateException()
                )

            var nextPageNumber: Int? = null
            var prevPageNumber: Int? = null

            if (response.page < response.total_pages) {
                nextPageNumber = response.page + 1
            }

            if (response.page > 1) {
                prevPageNumber = response.page - 1
            }

            LoadResult.Page(
                data = response.data,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

}