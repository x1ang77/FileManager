package com.xiangze.filemanager.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.filemanager.databinding.ItemLayoutImageBinding
import java.io.File

class ImageAdapter(var files: List<File>, val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<ImageAdapter.FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding =
            ItemLayoutImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val item = files[position]
        holder.binding.run {
            val uri = Uri.fromFile(item)
            ivImage.setImageURI(uri)
//            tvFileName.text = item.name
            cvImage.setOnClickListener {
                onClick(position)
            }
        }
    }

    override fun getItemCount() = files.size

    inner class FileViewHolder(val binding: ItemLayoutImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val image = files[adapterPosition]
            binding.run {
                ivImage.setImageURI(Uri.fromFile(image))

                cvImage.setOnClickListener {
                    onClick(adapterPosition)
                }
            }
        }
    }

}