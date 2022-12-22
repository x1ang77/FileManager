package com.xiangze.filemanager.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.filemanager.databinding.FullImageLayoutBinding
import java.io.File

class ImageSliderAdapter(private var images: List<File>): RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = FullImageLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = images[position]
        holder.binding.ivImage.setImageURI(Uri.fromFile(item))
    }

    override fun getItemCount() = images.size

    class ImageViewHolder(val binding: FullImageLayoutBinding):RecyclerView.ViewHolder(binding.root)
}