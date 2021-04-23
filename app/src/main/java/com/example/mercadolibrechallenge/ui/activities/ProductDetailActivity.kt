package com.example.mercadolibrechallenge.ui.activities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.databinding.ActivityProductDetailBinding
import com.example.mercadolibrechallenge.di.base.BaseActivity
import com.example.mercadolibrechallenge.network.responses.Product
import com.example.mercadolibrechallenge.utils.AppConstants
import com.squareup.picasso.Picasso
import org.jetbrains.anko.startActivity

class ProductDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: Product

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
            tvStateAndSold.text = getString(
                R.string.state_and_sold,
                productCondition(product.condition),
                product.sold_quantity.toString(),
                productSoldQuantity(
                    product.sold_quantity
                )
            )
            tvName.text = product.title
//            Picasso.with(this@ProductDetailActivity).load(product.pictures[0].url).fit().into(ivThumbnail)
            tvPrice.text = getString(R.string.price, product.price.toString())

            tvQuantity.text = getString(R.string.quantity, "1")
            tvAvailable.text = getString(R.string.availables, product.available_quantity.toString())

            Picasso.with(this@ProductDetailActivity)
                .load(product.pictures[0].url)
                .into(object: com.squareup.picasso.Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        ivThumbnail.setImageBitmap(bitmap)
                    }

                    override fun onBitmapFailed(errorDrawable: Drawable?) {}
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