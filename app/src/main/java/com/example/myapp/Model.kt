package com.example.myapp

data class News(
    val title: String,
    val date: String,
    var author_name: String,
    val thumbnail_pic_s: String,
    val url: String
)

data class NewsResponse(
    val reason: String,
    val result: NewsResult,
    val error_code: Int
)

data class NewsResult(
    val stat: String,
    val data: List<News>
)
