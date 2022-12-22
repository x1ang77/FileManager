package com.xiangze.filemanager.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.xiangze.filemanager.MainActivity
import com.xiangze.filemanager.R
import com.xiangze.filemanager.adapters.ImageSliderAdapter
import com.xiangze.filemanager.databinding.FragmentImageViewerBinding
import kotlinx.coroutines.delay
import java.io.File

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

        val args: ImageViewerFragmentArgs by navArgs()

        val images = (requireActivity() as MainActivity).images
        Log.d("debugging", images.size.toString())

        val image = images[args.pos]

        adapter = ImageSliderAdapter(images)

        binding.vpImage.let { viewPager ->
            viewPager.adapter = adapter
            viewPager.post{
                viewPager.setCurrentItem(args.pos,false)
            }
            viewPager.getChildAt(args.pos)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

//            val composePageTransformer = CompositePageTransformer()
//            composePageTransformer.addTransformer { page, position ->
//                val r = 1 - kotlin.math.abs(position)
//                page.scaleY = 0.90f + r * 0.10f
//            }
//            viewPager.setPageTransformer(composePageTransformer)
//
//            lifecycleScope.launchWhenResumed {
//                var position = 0
//                while(true) {
//                    delay(4000)
//                    position = (position + 1) % 12
//                    viewPager.setCurrentItem(position, true)
//                }
//            }
        }
    }
}