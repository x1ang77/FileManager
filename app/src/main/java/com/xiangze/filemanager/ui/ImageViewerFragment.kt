package com.xiangze.gallery.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.xiangze.filemanager.MainActivity
import com.xiangze.filemanager.databinding.FragmentImageViewerBinding
import com.xiangze.gallery.MainActivity
import com.xiangze.gallery.adapters.ImageSliderAdapter
import com.xiangze.gallery.databinding.FragmentImageViewerBinding

class ImageViewerFragment : Fragment() {
    private lateinit var binding: FragmentImageViewerBinding
    private lateinit var adapter: ImageSliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageViewerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navArgs: ImageViewerFragmentArgs by navArgs()

        val images = (requireActivity() as MainActivity).images

//        val image = images[navArgs.position]

        adapter = ImageSliderAdapter(images)

        binding.vpImages.let { viewPager ->
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 3
            viewPager.getChildAt(navArgs.position)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }
}