package com.example.pagingv3

data class InfoAndData(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<Item>
)