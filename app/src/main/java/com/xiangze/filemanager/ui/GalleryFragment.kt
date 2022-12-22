package com.xiangze.gallery.fragments

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.xiangze.filemanager.adapters.ImageAdapter
import com.xiangze.filemanager.databinding.FragmentGalleryBinding
import com.xiangze.gallery.MainActivity
import com.xiangze.gallery.adapters.ImageAdapter
import com.xiangze.gallery.databinding.FragmentHomeBinding
import java.io.File

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var adapter: ImageAdapter
    private val images: MutableList<File> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val path = Environment.getExternalStorageDirectory().path
        getImages(path)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navArgs: HomeFragmentArgs by navArgs()

        (requireActivity() as MainActivity).images = images
        setupAdapter(images)
    }

    private fun getImages(path: String) {
        val root = File(path)
        val files = root.listFiles()?.toList() ?: listOf()
        for (file in files) {
            if (Regex(".jpg|.png|.jpeg").containsMatchIn(file.name)) {
                images.add(file)
            }
        }
        for (file in files) {
            if (file.isDirectory) {
                getImages(file.path)
            }
        }
    }

    // droid4x
    // animotion??

    private fun setupAdapter(files: List<File>) {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = ImageAdapter(files) {
            val action = HomeFragmentDirections.actionHomeToImageViewer(it)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvFiles.layoutManager = layoutManager
        binding.rvFiles.adapter = adapter
    }
}