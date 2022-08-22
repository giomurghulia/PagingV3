package com.example.pagingv3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn

import kotlinx.coroutines.flow.Flow

class MainActivityViewModel : ViewModel() {

    fun getListData(): Flow<PagingData<Item>> {
        return Pager(config = PagingConfig(pageSize = 6, prefetchDistance = 1),
            pagingSourceFactory = { ItemPaging() }).flow.cachedIn(viewModelScope)
    }
}