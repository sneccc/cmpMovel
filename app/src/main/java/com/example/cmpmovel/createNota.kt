package com.example.cmpmovel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class createNota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_nota)


    }

    override fun onStart() {
        super.onStart()

        supportActionBar?.setTitle("Criar Nota")


    }

    fun adicionar1(view: View) {
        var texto = findViewById<EditText>(R.id.editTexto)
        var descricao = findViewById<EditText>(R.id.editDescricao)

        val i = Intent(this, Notas::class.java)
        i.putExtra("texto", texto.text.toString())
        i.putExtra("descricao", descricao.text.toString())
        startActivity(i)

    }

}