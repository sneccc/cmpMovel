package com.example.cmpmovel.api

import retrofit2.Call
import retrofit2.http.*
interface EndPoints {



    @GET("/mySlim/api/reportes")
    fun getReportes():Call<List<Reporte>>

    @FormUrlEncoded
    @POST("/mySlim/api/reportes")
    fun postTest(@Field("titulo")titulo:String?,@Field("descricao")descricao:String?):Call<Reporte>//Testar AutoIncrement @Field("id") id:Int?



    @POST("/mySlim/api/reportes/delete/{id}")
    fun deleteReporte(@Path("id")id:Int):Call<Reporte>

    @FormUrlEncoded
    @POST("/mySlim/api/reportes/edit/{id}")
    fun editReporte(@Path("id")id:Int,@Field("descricao")descricao:String?,@Field("titulo")titulo:String?):Call<Reporte>


    //@PUT("/mySlim/api/reportes/{id}")
    //fun putReport(@Path("id")id:Int):Call<Reporte>

}