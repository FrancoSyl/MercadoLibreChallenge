package com.example.mercadolibrechallenge.ui.activities

import android.content.Context
import android.os.Bundle
import android.widget.AbsListView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.databinding.ActivityProductDetailBinding
import com.example.mercadolibrechallenge.di.base.BaseActivity
import com.example.mercadolibrechallenge.network.responses.Product
import com.example.mercadolibrechallenge.ui.adapters.PicturesAdapter
import com.example.mercadolibrechallenge.utils.AppConstants
import org.jetbrains.anko.startActivity

class ProductDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: Product
    private lateinit var picturesAdapter: PicturesAdapter
    private var scrollPosition = 0
    private var scrollIncrease = false
    private var currentPosition = 1

    companion object {
        private const val PRODUCT = "product"

        fun start(context: Context, product: Product) = context.startActivity<ProductDetailActivity>(
            PRODUCT to product
        )
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
            picturesAdapter = PicturesAdapter()
            picturesAdapter.swapData(product.pictures)
            rvPictures.adapter = picturesAdapter

            tvStateAndSold.text = getString(
                R.string.state_and_sold,
                productCondition(product.condition),
                product.sold_quantity.toString(),
                productSoldQuantity(
                    product.sold_quantity
                )
            )
            tvName.text = product.title
            tvCurrentItem.text = getString(R.string.current_item_count, currentPosition.toString(), product.pictures.size.toString())
            tvPrice.text = getString(R.string.price, product.price.toString())

            tvQuantity.text = getString(R.string.quantity, "1")
            tvAvailable.text = getString(R.string.availables, product.available_quantity.toString())

            rvPictures.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    scrollIncrease = dx > 0
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                        val maxItem: Int = rvPictures.adapter?.itemCount?.minus(1)!!
                        if (scrollIncrease) {
                            currentPosition += 1
                            scrollPosition += 1
                            if (scrollPosition > maxItem) {
                                currentPosition = maxItem + 1
                                scrollPosition = maxItem
                            }
                        } else {
                            currentPosition -= 1
                            scrollPosition -= 1
                            if (scrollPosition < 0) {
                                currentPosition = 1
                                scrollPosition = 0
                            }
                        }
                        rvPictures.smoothScrollToPosition(scrollPosition)
                        tvCurrentItem.text = getString(R.string.current_item_count, currentPosition.toString(), product.pictures.size.toString())
                    }
                }
            })

        }
    }

    private fun productCondition(condition: String?): String {
        if (condition == "new"){
            return AppConstants.NEW
        }
        return AppConstants.USED
    }

    private fun productSoldQuantity(itemSold: Int?): String {
        if (itemSold == 1){
            return "vendido"
        }
        return "vendidos"
    }
}