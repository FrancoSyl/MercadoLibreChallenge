package com.example.mercadolibrechallenge.ui.activities

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.controllers.ObjectsController
import com.example.mercadolibrechallenge.databinding.ActivityProductDetailBinding
import com.example.mercadolibrechallenge.di.base.BaseActivity
import com.example.mercadolibrechallenge.ui.adapters.ProductsAdapter
import org.jetbrains.anko.startActivity

class ProductDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    companion object {
        fun start(context: Context) = context.startActivity<ProductDetailActivity>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@ProductDetailActivity, R.layout.activity_product_detail
        )

        binding.apply {

        }
    }
}