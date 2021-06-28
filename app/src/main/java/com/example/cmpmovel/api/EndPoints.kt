package com.example.cmpmovel.api

import retrofit2.Call
import retrofit2.http.*
interface EndPoints {



    @GET("/mySlim/api/reportes")
    fun getReportes():Call<List<Reporte>>

    @FormUrlEncoded
    @POST("/mySlim/api/reportes")
    fun postTest(@Field("titulo")titulo:String?,@Field("descricao")descricao:String?,@Field("id") id:Int?):Call<Reporte>

}