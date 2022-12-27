package com.xiangze.filemanager.adapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xiangze.filemanager.R
import com.xiangze.filemanager.databinding.ItemLayoutImageBinding
import java.io.File

class ImageAdapter(
    var images: List<File>,
    val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ItemLayoutImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageViewHolder(val binding: ItemLayoutImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind() {
                Log.d("bruh",adapterPosition.toString())
                val image = images[adapterPosition]
                binding.run {
                    Glide.with(binding.root).load(image).placeholder(R.drawable.ic_image).into(ivImage)

                    cvImage.setOnClickListener {
                        onClick(adapterPosition)
                    }
                }
            }
        }
}