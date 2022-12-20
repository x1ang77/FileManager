package com.xiangze.filemanager.fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiangze.filemanager.R
import com.xiangze.filemanager.adapters.FileAdapter
import com.xiangze.filemanager.databinding.FragmentHomeBinding
import java.io.File

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: FileAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val path = Environment.getExternalStorageDirectory().path
        val root = File(path)
        for (file in root.listFiles()!!) {
            Log.d("path", file.name)
        }
        root.listFiles()?.let {
            setupAdapter(it.toList())
        }
    }

    private fun setupAdapter(files: List<File>) {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = FileAdapter(files)
        binding.rvFiles.layoutManager = layoutManager
        binding.rvFiles.adapter = adapter
    }
}