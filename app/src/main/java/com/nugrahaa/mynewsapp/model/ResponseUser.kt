package com.nugrahaa.mynewsapp.model

import com.google.gson.annotations.SerializedName

data class ResponseUser (

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<Article>? = null

)