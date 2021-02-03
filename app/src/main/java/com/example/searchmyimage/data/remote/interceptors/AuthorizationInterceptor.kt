package com.example.searchmyimage.data.remote.interceptors

import com.example.searchmyimage.BuildConfig
import com.example.searchmyimage.utils.ConstantClass
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
         val original =chain.request()
         val httpUrl=original.url()
         val interceptedUrl=httpUrl.newBuilder()
             .addQueryParameter("key",BuildConfig.API_KEY)
             .build()
        val interceptedRequest=original.newBuilder().url(interceptedUrl).build()
        return chain.proceed(interceptedRequest)
    }
}