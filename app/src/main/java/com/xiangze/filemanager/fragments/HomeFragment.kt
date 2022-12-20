package com.xiangze.filemanager.fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiangze.filemanager.adapters.FileAdapter
import com.xiangze.filemanager.databinding.FragmentHomeBinding
import java.io.File

// Remember to download images from Pexels to see image files in Download folder
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

        val args: HomeFragmentArgs by navArgs()
        val path = if (args.path != null && args.path != "null") {
            args.path!!
        } else {
            Environment.getExternalStorageDirectory().path
        }
        val root = File(path)
        if (root.listFiles().isNullOrEmpty()) {
            binding.ivEmpty.isVisible = true
        }
        root.listFiles()?.let {
            setupAdapter(it.toList())
        }
//        for (file in root.listFiles()!!) {
//            Log.d("debugging", file.name)
//        }
    }

    private fun setupAdapter(files: List<File>) {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = FileAdapter(files) {
            if (it.isDirectory) {
                val directory = HomeFragmentDirections.actionHomeFragmentSelf(it.path)
                NavHostFragment.findNavController(this).navigate(directory)
            } else {
                val directory = HomeFragmentDirections.actionHomeFragmentToImageFragment(it.path)
                NavHostFragment.findNavController(this).navigate(directory)
            }
        }
        binding.rvFiles.adapter = adapter
        binding.rvFiles.layoutManager = layoutManager
    }
}