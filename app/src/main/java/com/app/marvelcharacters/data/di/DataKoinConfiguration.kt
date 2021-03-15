package com.app.marvelcharacters.data.di

import com.app.marvelcharacters.data.*
import com.app.marvelcharacters.data.datasource.CharactersRemoteDataSource
import com.app.marvelcharacters.data.datasource.CharactersRemoteDataSourceImpl
import com.app.marvelcharacters.domain.CharactersRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataKoinConfiguration(private val baseUrl: String) {

        fun getModule() = module {
            single { AuthInterceptor()}
            single { createRetrofit(get()) }
            single { createRetrofitImpl<CharactersRetrofit>(get()) }

            single<CharactersRepository> { CharactersRepositoryImpl(get())  }
            single<CharactersRemoteDataSource> { CharactersRemoteDataSourceImpl(get())  }
        }


        private fun createRetrofit(interceptor: AuthInterceptor) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client( OkHttpClient.Builder().addInterceptor(interceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private inline fun <reified T> createRetrofitImpl(retrofit: Retrofit): T =
        retrofit.create(T::class.java)
}