package com.example.mercadolibrechallenge.network.responses

import android.os.Parcelable
import com.example.mercadolibrechallenge.utils.AppConstants
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product (
    val id: String? = AppConstants.EMPTY_STRING,
    val title: String? = AppConstants.EMPTY_STRING,
    val price: Float? = 0F,
    val thumbnail: String? = AppConstants.EMPTY_STRING,
    val condition: String? = AppConstants.EMPTY_STRING,
    val sold_quantity: Int? = 0,
    val available_quantity: Int? = 0
) : Parcelable