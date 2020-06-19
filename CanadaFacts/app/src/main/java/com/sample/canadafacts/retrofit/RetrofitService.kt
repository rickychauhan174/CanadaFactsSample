package com.sample.canadafacts.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    val baseUrl = "https://dl.dropboxusercontent.com/"
    val gson = GsonBuilder().create()!!
    var client = OkHttpClient()

    fun retrofitApiRef(): ApiInterface {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
        client = builder.build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .client(client)
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}
