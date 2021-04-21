package com.example.mercadolibrechallenge.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibrechallenge.databinding.ListItemProductBinding
import com.example.mercadolibrechallenge.network.responses.Product

class ProductsViewHolder(private var binding: ListItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind (product: Product){
        binding.apply {
            tvName.text = product.title
        }
    }

}