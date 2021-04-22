package com.example.mercadolibrechallenge.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.databinding.ListItemProductBinding
import com.example.mercadolibrechallenge.network.responses.Product
import com.squareup.picasso.Picasso

class ProductsViewHolder(private var binding: ListItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind (product: Product, onProductClick: (product: Product) -> Unit){
        binding.apply {
            val price = product.price?.toInt()
            val context = this.root.context

            tvName.text = product.title
            tvPrice.text = context.getString(R.string.price, price.toString())
            if (price ?: 0 > 3500){
                tvFreeDelivery.visibility = View.VISIBLE
            } else {
                tvFreeDelivery.visibility = View.INVISIBLE
            }

            Picasso.with(context).load(product.thumbnail).fit().into(ivPhoto)

            containerProduct.setOnClickListener {
                onProductClick(product)
            }
        }
    }
}