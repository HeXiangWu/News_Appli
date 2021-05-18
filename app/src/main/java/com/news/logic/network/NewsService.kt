package com.news.logic.network

import com.news.logic.model.NewsResponse
import com.news.NewsApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//Retrofit的接口文件以具体功能种类名开头，以service结尾
interface NewsService {
    // 拼接请求的参数
    @GET("index?key=${NewsApplication.KEY}")
    fun searchNews(@Query("type") type: String): Call<NewsResponse>
}