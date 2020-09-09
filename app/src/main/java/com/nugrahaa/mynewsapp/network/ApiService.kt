package com.nugrahaa.mynewsapp.network

import com.nugrahaa.mynewsapp.model.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    fun getListNewsByName(@Query("q") q: String?, @Query("apiKey") apiKey: String?): Call<ResponseUser>

}