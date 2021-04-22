package com.example.mercadolibrechallenge.ui.activities

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.databinding.ActivityProductDetailBinding
import com.example.mercadolibrechallenge.di.base.BaseActivity
import com.example.mercadolibrechallenge.network.responses.Product
import org.jetbrains.anko.startActivity

class ProductDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: Product

    companion object {
        private const val PRODUCT = "product"

        fun start(context: Context, product: Product) = context.startActivity<ProductDetailActivity>(PRODUCT to product)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@ProductDetailActivity, R.layout.activity_product_detail
        )

        if (intent?.extras?.containsKey(PRODUCT) == true) {
            intent?.extras?.getParcelable<Product>(PRODUCT).let {
                product = it as Product
            }
        }

        binding.apply {
            tvName.text = product.title
        }
    }
}