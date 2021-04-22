package com.example.mercadolibrechallenge.network

import com.example.mercadolibrechallenge.network.responses.ResponseSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {
    @GET("search?category=MLA1055")
    fun getProducts(): Call<ResponseSearch>

    @GET("search?")
    fun getProductsBySearch(@Query("q") search: String): Call<ResponseSearch>
}