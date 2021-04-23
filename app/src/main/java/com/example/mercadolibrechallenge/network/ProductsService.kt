package com.example.mercadolibrechallenge.network

import com.example.mercadolibrechallenge.network.responses.ResponseProduct
import com.example.mercadolibrechallenge.network.responses.ResponseSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {
    @GET("sites/MLA/search?category=MLA1055")
    fun getProducts(): Call<ResponseSearch>

    @GET("items?")
    fun getProductDetail(@Query("ids") search: String): Call<ArrayList<ResponseProduct>>

    @GET("sites/MLA/search?")
    fun getProductsBySearch(@Query("q") search: String): Call<ResponseSearch>

}