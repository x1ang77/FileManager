package com.xiangze.filemanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.xiangze.filemanager.adapters.ViewPagerAdapter
import com.xiangze.filemanager.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

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
        val adapter = ViewPagerAdapter(
            listOf(FilesFragment.getInstance(),GalleryFragment.getInstance()),
            requireActivity().supportFragmentManager,
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
        }
    }
}