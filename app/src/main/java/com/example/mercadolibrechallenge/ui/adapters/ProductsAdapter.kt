package com.example.mercadolibrechallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.databinding.ListItemProductBinding
import com.example.mercadolibrechallenge.network.responses.Product
import com.example.mercadolibrechallenge.ui.viewholders.ProductsViewHolder

class ProductsAdapter : RecyclerView.Adapter<ProductsViewHolder>() {

    private var productsList = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding: ListItemProductBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_product,
            parent,
            false
        )
        return ProductsViewHolder(binding)
    }

    override fun getItemCount(): Int = productsList.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(product = productsList[position])
    }

    fun swapData(searchList: ArrayList<Product>) {
        this.productsList = searchList
    }

}