package com.sample.canadafacts.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.canadafacts.retrofit.RetrofitService
import com.sample.canadafacts.model.FactsResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactsViewModel(application: Application) : AndroidViewModel(application) {
    var factsLiveData: MutableLiveData<FactsResponseModel>? = null

    fun getFacts(): MutableLiveData<FactsResponseModel> {
        factsLiveData = MutableLiveData()
        val factsUrl = "s/2iodh4vg0eortkl/facts.json"
        val retrofitCall = RetrofitService.retrofitApiRef().factsList(factsUrl)
        retrofitCall.enqueue(object : Callback<FactsResponseModel> {

            override fun onResponse(
                call: Call<FactsResponseModel>,
                response: Response<FactsResponseModel>
            ) {
                if (response.isSuccessful) {
                    factsLiveData!!.value = response.body()

                } else {
                    factsLiveData!!.value = null
                }
            }

            override fun onFailure(call: Call<FactsResponseModel>, t: Throwable?) {
                factsLiveData = null
            }
        })
        return factsLiveData!!
    }
}
