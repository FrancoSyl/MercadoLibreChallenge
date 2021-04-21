package com.example.mercadolibrechallenge.network.responses

import com.example.mercadolibrechallenge.utils.AppConstants

data class Product (
    val id: String? = AppConstants.EMPTY_STRING,
    val title: String? = AppConstants.EMPTY_STRING
        )