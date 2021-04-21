package com.example.mercadolibrechallenge.network.responses

data class ResponseSearch (
    val results: List<Product> = ArrayList()
)