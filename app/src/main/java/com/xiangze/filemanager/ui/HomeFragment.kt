package com.xiangze.filemanager.ui

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val args: HomeFragmentArgs by navArgs()

        val path = if(args.path != null && args.path != "null") {
            args.path!!
        } else {
            Environment.getExternalStorageDirectory().path
        }

        val root = File(path)
        root.listFiles()?.let {
            setupAdapter(it.toList())
            if(it.toList().isNullOrEmpty()) {
                binding.ivEmpty.setImageResource(R.drawable.ic_nofile)
            } else {
                binding.rvFiles.setBackgroundResource(R.color.app_bg)
            }
        }

    }
    private fun setupAdapter(files: List<File>) {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = FileAdapter(files) {
            val action = HomeFragmentDirections.actionHomeFragmentSelf(it.path)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvFiles.layoutManager = layoutManager
        binding.rvFiles.adapter = adapter
    }
}