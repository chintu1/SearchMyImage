package com.example.searchmyimage.ui.fullscreenimage.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.searchmyimage.databinding.FullScreenImageFragmentBinding
import com.example.searchmyimage.ui.fullscreenimage.viewmodel.FullScreenImageViewModel
import com.example.searchmyimage.utils.loadImage

class FullScreenImageFragment : Fragment() {

    companion object {
        fun newInstance() = FullScreenImageFragment()
    }

    val viewModel by viewModels<FullScreenImageViewModel>()
    lateinit var fullScreenImageFragmentBinding: FullScreenImageFragmentBinding
    var imageUrl: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fullScreenImageFragmentBinding = FullScreenImageFragmentBinding.inflate(
            LayoutInflater.from(requireContext()),
            container,
            false
        )
        return fullScreenImageFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage()
        addIntegers(1,2,lamdaFun)
        addIntegers(5,6,::sum)
    }

    val lamdaFun : (Int,Int)->Int = {a,b->a+b}
    fun sum(a:Int,b:Int):Int{
        return a+b;
    }

    fun addIntegers(a:Int,b:Int,addNumber:(Int,Int)->Int):Int{
        return addNumber(a,b)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageUrl = arguments?.getString("image_url") ?: ""
    }

    private fun loadImage() {
        fullScreenImageFragmentBinding.fullscreenImageImageview.loadImage(
            requireContext(),
            imageUrl
        )
    }
}