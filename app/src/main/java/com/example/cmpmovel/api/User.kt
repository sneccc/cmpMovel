package com.example.cmpmovel.api



data class User (
    val id:Int,
    val name:String,
    val email:String,
    val address: Address
)

data class  Address(
        val city: String,
        val zipcode:String
)


data class Reporte(
    val id:Int,
    val titulo:String,
    val descricao:String
)