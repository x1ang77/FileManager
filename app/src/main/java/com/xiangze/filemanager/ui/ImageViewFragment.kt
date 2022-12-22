package com.xiangze.gallery.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.filemanager.databinding.FragmentImageViewBinding
import com.xiangze.gallery.adapters.ImagesSliderAdapter


class ImageViewFragment : Fragment() {
    private lateinit var adapter: ImagesSliderAdapter
    private lateinit var binding: FragmentImageViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ImageViewFragmentArgs by navArgs()
        adapter = ImagesSliderAdapter(mutableListOf())
        val images = (requireActivity() as MainActivity).images
        val image = images[args.pos]

        binding.ivImages.let{ viewPager ->
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 3
            viewPager.getChildAt(args.pos)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

    }

}