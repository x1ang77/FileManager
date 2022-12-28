package com.xiangze.filemanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xiangze.filemanager.R
import com.xiangze.filemanager.databinding.ItemLayoutFileBinding
import java.io.File

class FileAdapter(
    var files: List<File>,
    val onClick: (file: File) -> Unit
) :
    RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding = ItemLayoutFileBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val item = files[position]
        holder.binding.run {
            if (item.isDirectory) {
                ivFile.setImageResource(R.drawable.ic_folder)
            } else if (Regex(".jpg|.jpeg|.png").containsMatchIn(item.path)) {
                Glide.with(holder.binding.root)
                    .load(item)
                    .placeholder(R.drawable.ic_image)
                    .into(ivFile)
            } else {
                ivFile.setImageResource(R.drawable.ic_file)
            }

            tvFileName.text = item.name

            cvFile.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun getItemCount() = files.size

    fun setItems(items: List<File>) {
        this.files = items
        notifyDataSetChanged()
    }

    class FileViewHolder(val binding: ItemLayoutFileBinding) : RecyclerView.ViewHolder(binding.root)

}