package com.xiangze.filemanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.xiangze.filemanager.adapters.ViewPagerAdapter
import com.xiangze.filemanager.databinding.FragmentMainBinding
import com.xiangze.filemanager.viewModels.GalleryViewModel
import com.xiangze.filemanager.viewModels.MainViewModel


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val filesFragment = FilesFragment.getInstance()
    private val galleryFragment = GalleryFragment.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), viewModel.greeting, Toast.LENGTH_LONG).show()

        val adapter = ViewPagerAdapter(
            listOf(filesFragment, galleryFragment),
            childFragmentManager,
            lifecycle
        )

        binding.vpFileManager.adapter = adapter
        TabLayoutMediator(binding.tlFileManager, binding.vpFileManager){ tab, pos ->
            tab.text = when(pos){
                0 -> "Files"
                else -> "Gallery"
            }
        }.attach()

        setFragmentResultListener("result_from_image_viewer"){ _, result ->

            val refresh = result.getBoolean("refresh")
            Snackbar.make(view,"Refresh: $refresh", Snackbar.LENGTH_LONG).show()
            galleryFragment.refresh()
        }
    }
}