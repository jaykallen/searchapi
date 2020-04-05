package com.jaykallen.searchapi.internet

import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.jaykallen.searchapi.model.Teams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestManager {
    private const val BASE_URL = "https://www.thesportsdb.com"
    var mutableLiveData = MutableLiveData<Teams>()

    fun getSpecificName(input: String) {
        val service = initiateRetrofit()
        val call = service.querySpecificTeam(input)
        println("Attempting URL: " + call.request().url())
        call.enqueue(object : Callback<Teams> {
            override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                println("Successful Query: " + response.body())
                val teams = response.body()
                mutableLiveData.value = teams
            }
            override fun onFailure(call: Call<Teams>, t: Throwable) {
                println("Failed Call: $t")
            }
        })
    }

    fun getAllNames(input: String) {
            val service = initiateRetrofit()
            val call = service.queryAllTeams(input)
            println("Attempting URL: " + call.request().url())
            call.enqueue(object : Callback<Teams> {
                override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                    println("Successful Query: " + response.body())
                    val teams = response.body()
                    mutableLiveData.value = teams
                }
                override fun onFailure(call: Call<Teams>, t: Throwable) {
                    println("Failed Call: $t")
                }
            })
    }

    private fun initiateRetrofit(): ApiService {
        val gSon = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gSon))
            .build()
        return retrofit.create(ApiService::class.java)
    }

}