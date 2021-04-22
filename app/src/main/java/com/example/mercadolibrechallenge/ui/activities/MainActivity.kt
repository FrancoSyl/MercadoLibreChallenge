package com.example.mercadolibrechallenge.ui.activities

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.controllers.ObjectsController
import com.example.mercadolibrechallenge.databinding.ActivityMainBinding
import com.example.mercadolibrechallenge.di.base.BaseActivity
import com.example.mercadolibrechallenge.ui.adapters.ProductsAdapter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productsAdapter: ProductsAdapter

    companion object {
        fun start(context: Context) = context.startActivity<MainActivity>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@MainActivity, R.layout.activity_main
        )

        binding.apply {
            productsAdapter = ProductsAdapter(onProductClick = { product ->
                ProductDetailActivity.start(this@MainActivity, product = product)
            })
            productsAdapter.swapData(ObjectsController.productsList)
            rvProducts.adapter = productsAdapter
        }
    }
}