package com.example.cmpmovel.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {

    //class que interage com o retrofit para fazer as chamadas http dos pedidos
    private val client = OkHttpClient.Builder().build()

    //Uma instancia do retrofit
    private val retrofit =Retrofit.Builder().baseUrl("https://myappecgm.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create()).client(client).build()

    fun<T> buildService(service :Class<T>):T{
        return retrofit.create(service)
    }
}