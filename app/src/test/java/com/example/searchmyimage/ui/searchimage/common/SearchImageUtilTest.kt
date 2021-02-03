package com.example.searchmyimage.ui.searchimage.common

import com.example.searchmyimage.utils.ConstantClass
import com.google.common.truth.Truth
import org.junit.Test

class SearchImageUtilTest{

    @Test
    fun empty_string_return_false() {
        val result = SearchImageUtil.isSearchStringValid("")
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun input_string_greater_than_max_length_return_false() {
        val stringBuilder=StringBuilder()
        for (i in 0..ConstantClass.MAX_SEARCH_INPUT_STRING_LENGTH+1){
            stringBuilder.append("c")
        }
        val result = SearchImageUtil.isSearchStringValid(stringBuilder.toString())
        Truth.assertThat(result).isFalse()
    }
}