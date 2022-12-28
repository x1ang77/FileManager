package com.xiangze.filemanager.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.filemanager.MainActivity
import com.xiangze.filemanager.adapters.ImageSliderAdapter
import com.xiangze.filemanager.databinding.FragmentImageViewerBinding

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

        adapter = ImageSliderAdapter(images)

        binding.vpImages.let { viewPager ->
            viewPager.adapter = adapter
            viewPager.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            viewPager.setCurrentItem(args.pos, false)
        }

        binding.btnBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("result_from_image_viewer", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}