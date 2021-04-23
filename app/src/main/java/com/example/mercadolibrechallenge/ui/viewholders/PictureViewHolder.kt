package com.example.mercadolibrechallenge.ui.viewholders

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibrechallenge.databinding.ListItemPictureBinding
import com.example.mercadolibrechallenge.network.responses.Picture
import com.squareup.picasso.Picasso

class PictureViewHolder(private var binding: ListItemPictureBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(picture: Picture) {
        binding.apply {
            val context = this.root.context

            Picasso.with(context)
                .load(picture.url)
                .into(object: com.squareup.picasso.Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        ivPicture.setImageBitmap(bitmap)
                    }

                    override fun onBitmapFailed(errorDrawable: Drawable?) {}
                })
        }
    }

}