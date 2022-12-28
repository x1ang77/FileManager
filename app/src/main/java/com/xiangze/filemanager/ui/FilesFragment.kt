package com.xiangze.filemanager.ui

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiangze.filemanager.adapters.FileAdapter
import com.xiangze.filemanager.databinding.FragmentFilesBinding
import java.io.File

class FilesFragment private constructor() : Fragment() {
    private lateinit var binding: FragmentFilesBinding
    private lateinit var adapter: FileAdapter

    private val stack = ArrayDeque<String>()

    // Stack, Queue, Deque

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(stack.isNotEmpty()) {
                    stack.removeFirst()
                    val root = File(stack.first())
                    root.listFiles()?.let {
                        adapter.setItems(it.toList())
                    }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val args: FilesFragmentArgs by navArgs()
//        val path = if(args.path != null && args.path != "null"){
//            args.path!!
//        }else{
//           Environment.getExternalStorageDirectory().path
       // }

        val path = Environment.getExternalStorageDirectory().path
        stack.addFirst(path)

        val root = File(path)
        if(root.listFiles()?.toList().isNullOrEmpty()){
            binding.emptyFile.isVisible = true
        }
        root.listFiles()?.let {
            setupAdapter(it.toList())
        }
    }

    private fun setupAdapter(files: List<File>){
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = FileAdapter(files){
            if(it.isDirectory) {
                stack.addFirst(it.path)
                it.listFiles()?.let { files ->
                    adapter.setItems(files.toList())
                }
            }
        }
        binding.rvFiles.layoutManager = layoutManager
        binding.rvFiles.adapter = adapter
    }


    companion object {
        private var filesFragmentInstance: FilesFragment? = null

        fun getInstance(): FilesFragment {
            if(filesFragmentInstance == null) {
                filesFragmentInstance = FilesFragment()
            }

            return filesFragmentInstance!!
        }
    }
}