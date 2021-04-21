package com.example.mercadolibrechallenge.network

import com.example.mercadolibrechallenge.network.responses.ResponseSearch
import retrofit2.Call
import retrofit2.http.GET

interface ProductsService {
    @GET("search?category=MLA1055")
    fun getProducts(): Call<ResponseSearch>
}