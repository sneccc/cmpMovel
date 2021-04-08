package com.example.cmpmovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

const val PARAM_VARIAVEL ="nome"
class PaginaInicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_inicial)
    }



    fun comecar(view: View) {
        var variavel = "exemplo de variavel"
        val intent=Intent(this,Notas::class.java).apply {
         putExtra(PARAM_VARIAVEL,variavel)
}
        startActivity(intent)
    }
}