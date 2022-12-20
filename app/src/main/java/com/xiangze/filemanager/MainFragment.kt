package com.xiangze.filemanager

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiangze.filemanager.adapters.FileAdapter
import com.xiangze.filemanager.databinding.FragmentMainBinding
import java.io.File

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: FileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: MainFragmentArgs by navArgs()
        val path = if(args.path != null && args.path != "null"){
            args.path!!
        }else{
            Environment.getExternalStorageDirectory().path
        }




        val root = File(path)
        root.listFiles()?.let {
            setupAdapter(it.toList())
            if(it.toList().isNullOrEmpty()){
                binding.ivEmpty.setBackgroundResource(R.drawable.ic_baseline_folder_off_24)
            }else{
                binding.ivEmpty.setBackgroundResource(R.drawable.bgnull)
            }
        }
    }

    private fun setupAdapter(files: List<File>){
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = FileAdapter(files){
            val action = MainFragmentDirections.actionMainToSelf(it.path)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvFiles.layoutManager = layoutManager
        binding.rvFiles.adapter = adapter
    }
}