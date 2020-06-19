package com.sample.canadafacts.retrofit

import com.sample.canadafacts.model.FactsResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun factsList(@Url moviesUrl: String): Call<FactsResponseModel>
}
