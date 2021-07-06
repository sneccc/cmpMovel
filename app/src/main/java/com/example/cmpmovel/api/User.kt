package com.example.cmpmovel.api


//Class que representa o que vamos receber como output do serviço


data class Reporte(
    val id:Int,
    val titulo:String,
    val descricao:String
)


//o que vou receber como output do request
data class Marcador(
        val id:Int,
        val titulo_marcador:String,
        val desc_marcador:String,
        val lat_marcador:Double,
        val lon_marcador: Double
)

