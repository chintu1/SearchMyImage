package com.example.searchmyimage.ui.searchimage.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.searchmyimage.data.model.PixabaySearchResult
import com.example.searchmyimage.data.repository.SearchImageRepository
import com.example.searchmyimage.ui.searchimage.common.SearchImageUtil
import com.example.searchmyimage.utils.ConstantClass
import com.example.searchmyimage.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(private val searchImageRepository: SearchImageRepository) :
    ViewModel() {

    private var searchQuery = MutableLiveData<String>(ConstantClass.DEFAULT_SEARCH_IMAGE_STRING)

    val searchImageResult = searchQuery.switchMap {
        liveData(Dispatchers.IO){
            try {
                emit(DataState.Loading)
                val imageListRespinse = searchImageRepository.getImage(it).pixabaySearchResults
                emit(DataState.Success(imageListRespinse))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    fun searchImage(s: String) {
        if (SearchImageUtil.isSearchStringValid(s))
            searchQuery.value = s
    }
}