package com.xiangze.filemanager.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.xiangze.filemanager.databinding.FragmentSecondBinding
import java.io.File

class SeconddFragment : Fragment() {
    private lateinit var binding : FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: SeconddFragmentArgs by navArgs()
        val file = File(args.path)
        val uri = Uri.fromFile(file)
        binding.ivImage.setImageURI(uri)
    }
}