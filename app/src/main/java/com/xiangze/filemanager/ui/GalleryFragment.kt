package com.xiangze.filemanager.ui

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.xiangze.filemanager.MainActivity
import com.xiangze.filemanager.databinding.FragmentGalleryBinding
import com.xiangze.filemanager.adapters.ImageAdapter
import com.xiangze.filemanager.viewModels.GalleryViewModel
import com.xiangze.filemanager.viewModels.MainViewModel
import java.io.File

class GalleryFragment private constructor() : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()
    private val parentViewModel: MainViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )
    private val images: MutableList<File> = mutableListOf()
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        images.clear()
        val path = Environment.getExternalStorageDirectory().path
        getImages(path)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).images = images
        setupAdapter(images)

//        binding.tvGreeting.text = "${viewModel.greeting}\n${parentViewModel.greeting}"

        setFragmentResultListener("from_image_viewer") { _, result ->
            val refresh = result.getBoolean("refresh")
            Snackbar.make(view, "Refresh: $refresh", Snackbar.LENGTH_LONG).show()
        }
    }

    fun refresh() {
        Snackbar.make(binding.root, "Refreshing", Snackbar.LENGTH_LONG).show()
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

    private fun setupAdapter(images: List<File>) {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = ImageAdapter(images) {
            val action = MainFragmentDirections.actionMainToImageViewer(it)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvImages.layoutManager = layoutManager
        binding.rvImages.adapter = adapter
    }

    companion object {
        private var galleryFragmentInstance: GalleryFragment? = null
        fun getInstance(): GalleryFragment {
            if (galleryFragmentInstance == null) {
                galleryFragmentInstance = GalleryFragment()
            }
            return galleryFragmentInstance!!
        }
    }
}