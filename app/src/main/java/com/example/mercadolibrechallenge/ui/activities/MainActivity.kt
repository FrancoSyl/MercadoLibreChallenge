package com.example.mercadolibrechallenge.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.controllers.ObjectsController
import com.example.mercadolibrechallenge.databinding.ActivityMainBinding
import com.example.mercadolibrechallenge.di.base.BaseActivity
import com.example.mercadolibrechallenge.network.ApiService
import com.example.mercadolibrechallenge.network.ProductsService
import com.example.mercadolibrechallenge.network.responses.Product
import com.example.mercadolibrechallenge.network.responses.ResponseSearch
import com.example.mercadolibrechallenge.ui.adapters.ProductsAdapter
import com.example.mercadolibrechallenge.utils.AppConstants
import kotlinx.android.synthetic.main.view_toolbar.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var service: ProductsService

    companion object {
        fun start(context: Context) = context.startActivity<MainActivity>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@MainActivity, R.layout.activity_main
        )
        service = ApiService.getRetrofitCommon(AppConstants.BASE_URL_TO_GET_PRODUCTS).create(ProductsService::class.java)

        binding.apply {
            toolbar.apply {
                etSearch.setOnEditorActionListener { v, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        containerEmptyList.isVisible = false
                        if (v.text.isEmpty()){
                            productsAdapter.swapData(ObjectsController.productsList)
                            productsAdapter.notifyDataSetChanged()
                        } else {
                            searchProduct(v.text.toString())
                        }
                    }
                    false
                }
            }

            productsAdapter = ProductsAdapter(onProductClick = { product ->
                ProductDetailActivity.start(this@MainActivity, product = product)
            })
            productsAdapter.swapData(ObjectsController.productsList)
            rvProducts.adapter = productsAdapter
        }
    }

    private fun searchProduct(search: String) {
        showProgress()
        val productsList = arrayListOf<Product>()
        service.getProductsBySearch(search).enqueue(object : Callback<ResponseSearch> {
            override fun onResponse(
                call: Call<ResponseSearch>,
                response: Response<ResponseSearch>
            ) {
                response.body()?.let {
                    it.results.forEach { product ->
                        productsList.add(product)
                    }
                    productsAdapter.swapData(productsList)
                    productsAdapter.notifyDataSetChanged()
                    binding.containerEmptyList.isVisible = productsList.size == 0
                    hideProgress()
                }
            }

            override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
                finish()
                toast("Error: ${t.message}")
                hideProgress()
            }
        })
    }
}