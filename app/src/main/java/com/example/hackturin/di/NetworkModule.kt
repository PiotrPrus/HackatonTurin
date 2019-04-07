package com.example.hackturin.di

import com.example.hackturin.BuildConfig
import com.example.hackturin.data.api.GeoFenceApi
import com.example.hackturin.data.api.WikiApi
import com.example.hackturin.utils.GFE_BASE_URL
import com.example.hackturin.utils.WIKI_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofitService<GeoFenceApi>(get(), GFE_BASE_URL) }
    single { provideRetrofitService<WikiApi>(get(), WIKI_BASE_URL) }
}

private fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
    }

    return builder.build()
}

private inline fun <reified T> provideRetrofitService(okHttpClient: OkHttpClient, url: String): T =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(T::class.java)