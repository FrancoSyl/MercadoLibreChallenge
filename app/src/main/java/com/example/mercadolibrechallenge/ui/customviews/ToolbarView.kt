package com.example.mercadolibrechallenge.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.view_toolbar.view.*

class ToolbarView : ConstraintLayout {

    var showIvMenu: Boolean = false

    var showTvProduct: Boolean = false

    var showEtProduct: Boolean = false

    var showIvLike: Boolean = false

    var showIvSearch: Boolean = false

    var showIvCart: Boolean = false

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        inflate(context, R.layout.view_toolbar, this)
        context.theme?.obtainStyledAttributes(attrs, R.styleable.ToolbarView, 0, 0)?.apply {
            try {
                showIvMenu = getBoolean(R.styleable.ToolbarView_toolbar_show_iv_menu, false)
                showTvProduct = getBoolean(R.styleable.ToolbarView_toolbar_show_tv_product, false)
                showEtProduct = getBoolean(R.styleable.ToolbarView_toolbar_show_et_search, false)
                showIvLike = getBoolean(R.styleable.ToolbarView_toolbar_show_iv_like, false)
                showIvSearch = getBoolean(R.styleable.ToolbarView_toolbar_show_iv_search, false)
                showIvCart = getBoolean(R.styleable.ToolbarView_toolbar_show_iv_cart, false)
                updateView()
            } finally {
                recycle()
            }
        }
    }

    private fun updateView() {
        ivMenu?.visibility = if (showIvMenu) {
            View.VISIBLE
        } else {
            View.GONE
        }

        tvProduct?.visibility = if (showTvProduct) {
            View.VISIBLE
        } else {
            View.GONE
        }

        etSearch?.visibility = if (showEtProduct) {
            View.VISIBLE
        } else {
            View.GONE
        }

        ivLike?.visibility = if (showIvLike) {
            View.VISIBLE
        } else {
            View.GONE
        }

        ivSearch?.visibility = if (showIvSearch) {
            View.VISIBLE
        } else {
            View.GONE
        }

        ivCart?.visibility = if (showIvCart) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}