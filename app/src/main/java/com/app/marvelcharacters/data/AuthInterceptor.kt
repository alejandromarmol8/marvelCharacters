package com.app.marvelcharacters.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object{
        const val TS = "ts"
        const val TS_VALUE = "1"
        const val API_KEY = "apikey"
        const val API_KEY_VALUE = "3d1a9ca0bf10ba7bdf29d10027f6b06e"
        const val HASH = "hash"
        const val HASH_VALUE = "9ef016a3485a8cd2f8f3c843c620d4a9"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
            .newBuilder()
            .addQueryParameter(TS, TS_VALUE)
            .addQueryParameter(API_KEY, API_KEY_VALUE)
            .addQueryParameter(HASH, HASH_VALUE)
            .build()
        return chain.proceed(chain.request().newBuilder().url(url).build())
    }
}