package com.example.searchmyimage.ui.searchimage.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.searchmyimage.MainActivity
import com.example.searchmyimage.databinding.SearchImageFragmentBinding
import com.example.searchmyimage.ui.base.BaseFragment
import com.example.searchmyimage.ui.searchimage.adapter.SearchImageAdapter
import com.example.searchmyimage.ui.searchimage.diffutills.SearchImageDiffUtil
import com.example.searchmyimage.ui.searchimage.viewmodel.SearchImageViewModel
import com.example.searchmyimage.utils.DataState
import com.example.searchmyimage.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SearchImageFragment : BaseFragment(), SearchView.OnQueryTextListener {

    private lateinit var searchImageFragmentBinding: SearchImageFragmentBinding

    @Inject
    lateinit var searchImageAdapter: SearchImageAdapter

    val mHandler = Handler()

    companion object {
        fun newInstance() = SearchImageFragment()
    }

    val viewModel by viewModels<SearchImageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchImageFragmentBinding = SearchImageFragmentBinding.inflate(
            LayoutInflater.from(requireContext()),
            container,
            false
        )
        return searchImageFragmentBinding.root
    }

    private fun addListeners() {
        searchImageFragmentBinding.imageSearchview.setOnQueryTextListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListeners()
        setUpSearchImageRecyclerView()
        setupViewModelObservables()
    }

    private fun setupViewModelObservables() {
        observerSearchImageResponse()
    }

    private fun observerSearchImageResponse() {
        viewModel.searchImageResult.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    showLoader()
                }
                is DataState.Error -> {
                    hideLoader()
                }
                is DataState.Success -> {
                    hideLoader()
                    searchImageAdapter.setData(it.data)
                }

            }
        }
    }

    private fun setUpSearchImageRecyclerView() {
        setSearchImageLayoutManager()
        setSearchImageAdapter()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        searchImageByName(p0)
        return true
    }

    override fun onQueryTextChange(searchString: String?): Boolean {
        mHandler.removeCallbacksAndMessages(null)
        mHandler.postDelayed(Runnable {
            searchImageByName(searchString)
        }, 600)
        return true
    }

    private fun searchImageByName(searchString: String?) {
        hideKeyboard()
        searchString?.let {
            viewModel.searchImage(it)
        }
    }

    private fun setSearchImageAdapter() {
        searchImageFragmentBinding.imageRecyclerview.adapter = searchImageAdapter
    }

    private fun setSearchImageLayoutManager() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        searchImageFragmentBinding.imageRecyclerview.layoutManager = layoutManager
    }

    private fun showLoader() {
        searchImageFragmentBinding.searchImageProgressbar.show()
    }

    private fun hideLoader() {
        searchImageFragmentBinding.searchImageProgressbar.hide()
    }
}