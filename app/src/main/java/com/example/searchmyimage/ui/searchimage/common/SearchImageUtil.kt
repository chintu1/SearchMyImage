package com.example.searchmyimage.ui.searchimage.common

import com.example.searchmyimage.utils.ConstantClass

object SearchImageUtil {

    fun isSearchStringValid(str: String): Boolean {
        return str.isNotEmpty() && str.length < ConstantClass.MAX_SEARCH_INPUT_STRING_LENGTH
    }
}