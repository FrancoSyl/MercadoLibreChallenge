package com.example.mercadolibrechallenge.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibrechallenge.databinding.ListItemProductBinding
import com.example.mercadolibrechallenge.network.responses.Product

class ProductsViewHolder(private var binding: ListItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind (product: Product, onProductClick: (product: Product) -> Unit){
        binding.apply {
            tvName.text = product.title
            tvPrice.text = "$ ${product.price?.toInt()}"

            containerProduct.setOnClickListener {
                onProductClick(product)
            }
        }
    }

}