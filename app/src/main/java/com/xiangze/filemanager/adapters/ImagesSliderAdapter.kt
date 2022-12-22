package com.xiangze.gallery.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.databinding.ImageViewBinding
import java.io.File

class ImagesSliderAdapter(var images: List<File>): RecyclerView.Adapter<ImagesSliderAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ImageViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = images[position]
        holder.binding.run{
            ivImageView.setImageURI(Uri.fromFile(item))
        }

    }


    override fun getItemCount() = images.size
}
