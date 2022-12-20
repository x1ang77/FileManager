package com.xiangze.filemanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.filemanager.R
import com.xiangze.filemanager.databinding.ItemLayoutFileBinding
import java.io.File

class FileAdapter(var files: List<File>, val onClick: (file: File) -> Unit) :
    RecyclerView.Adapter<FileAdapter.FileViewHolder>() {
    class FileViewHolder(val binding: ItemLayoutFileBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding = ItemLayoutFileBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        holder.binding.run {
            if (file.isDirectory) {
                ivFile.setImageResource(R.drawable.ic_folder)
            } else {
                ivFile.setImageResource(R.drawable.ic_file)
            }

            tvFileName.text = file.name
            cvFile.setOnClickListener {
                onClick(file)
            }
        }
    }

    // same as: override fun getItemCount(): Int { return files.size }
    override fun getItemCount() = files.size
}