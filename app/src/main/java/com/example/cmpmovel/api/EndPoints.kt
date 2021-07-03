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



    //------------- Marcadores para o mapa -------------------------

    //Obter todos os Marcadores
    @GET("/mySlim/api/marcadores")
    fun getMarcadores():Call<List<Marcador>>

    //Criar um marcador
    @FormUrlEncoded
    @POST("/mySlim/api/marcadores")
    fun postMarcador(@Field("titulo_marcador")titulo_marcador:String?,@Field("desc_marcador")desc_marcador:String?,@Field("lat_marcador")lat_marcador:Double?,@Field("lat_marcador")lon_marcador:Double?):Call<Marcador>

    //Eliminar Marcador
    @POST("/mySlim/api/marcadores/delete/{id}")
    fun deleteMarcador(@Path("id")id:Int):Call<Marcador>

    //Obter o resultado de um Marcador Através do ID
    @GET("/mySlim/api/marcadores/{id}")
    fun getMarcadoresByID(@Path("id")id:Int):Call<List<Marcador>>

    //Editar pelo ID
    @FormUrlEncoded
    @POST("/mySlim/api/marcadores/edit/{id}")
    fun editMarcador(@Path("id")id:Int,@Field("desc_marcador")desc_marcador:String?,@Field("titulo_marcador")titulo_marcador:String?,
                     @Field("lat_marcador")lat_marcador:Double?,@Field("lon_marcador")lon_marcador:Double?):Call<Marcador>
}