package com.xiangze.filemanager.fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
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
        val navArgs: HomeFragmentArgs by navArgs()

        val path = if (navArgs.path != null && navArgs.path != "null") {
            navArgs.path!!
        } else {
            Environment.getExternalStorageDirectory().path
        }

        val root = File(path)

        // khayrul
        if (root.listFiles().isNullOrEmpty()) {
            binding.ivFolderEmpty.isVisible = true
            binding.tvFolderEmpty.isVisible = true
        }
        root.listFiles()?.let {
            setupAdapter(it.toList())
        }

        // mine
//        root.listFiles()?.let {
//            if (it.isEmpty()) {
//                binding.ivFolderEmpty.visibility = View.VISIBLE
//                binding.tvFolderEmpty.visibility = View.VISIBLE
//            } else {
//                binding.ivFolderEmpty.visibility = View.GONE
//                binding.tvFolderEmpty.visibility = View.GONE
//                setupAdapter(it.toList())
//            }
//        }
    }

    private fun setupAdapter(files: List<File>) {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = FileAdapter(files) {
            if (it.isDirectory) {
                val action = HomeFragmentDirections.actionHomeToSelf(it.path)
                NavHostFragment.findNavController(this).navigate(action)
            } else {
                val action = HomeFragmentDirections.actionHomeToImage(it.path)
                NavHostFragment.findNavController(this).navigate(action)
            }
        }
        binding.rvFiles.layoutManager = layoutManager
        binding.rvFiles.adapter = adapter
    }
}