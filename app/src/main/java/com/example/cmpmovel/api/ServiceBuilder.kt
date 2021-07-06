package com.example.cmpmovel.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {


    val interceptor=HttpLoggingInterceptor().apply {
        this.level=HttpLoggingInterceptor.Level.BODY
    }


    //class que interage com o retrofit para fazer as chamadas http dos pedidos
    private val client = OkHttpClient.Builder().apply { this.addInterceptor(interceptor) }.build()


    var gson = GsonBuilder()
        .setLenient()
        .create()

    //Uma instancia do retrofit GsonConverterFactory.create()
    private val retrofit =Retrofit.Builder().baseUrl("https://myappecgm.000webhostapp.com/").addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()

    fun<T> buildService(service :Class<T>):T{
        return retrofit.create(service)
    }
}