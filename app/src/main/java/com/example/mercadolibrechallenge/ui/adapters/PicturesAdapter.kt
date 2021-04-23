package com.example.mercadolibrechallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibrechallenge.R
import com.example.mercadolibrechallenge.databinding.ListItemPictureBinding
import com.example.mercadolibrechallenge.network.responses.Picture
import com.example.mercadolibrechallenge.ui.viewholders.PictureViewHolder

class PicturesAdapter: RecyclerView.Adapter<PictureViewHolder>() {

    private var picturesList = ArrayList<Picture>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding: ListItemPictureBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_picture,
            parent,
            false
        )
        return PictureViewHolder(binding)
    }

    override fun getItemCount(): Int = picturesList.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(picture = picturesList[position])
    }

    fun swapData(picturesList: ArrayList<Picture>) {
        this.picturesList = picturesList
    }

}