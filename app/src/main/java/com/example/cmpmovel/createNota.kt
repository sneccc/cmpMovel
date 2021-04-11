package com.example.cmpmovel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class createNota : AppCompatActivity() {

    private lateinit var titulo: EditText
    private lateinit var descricao: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_nota)

        titulo = findViewById(R.id.editTitulo)
        descricao = findViewById(R.id.editDescricao)

        val button = findViewById<Button>(R.id.adicionar1)
        button.setOnClickListener {
            Toast.makeText(this,"CLICOU",Toast.LENGTH_LONG)
            Log.d( "consoleTAG", "ClickListener")
            val replyIntent = Intent()
            if (TextUtils.isEmpty(titulo.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
                Log.d( "consoleTAG", "Está empty")
            } else {
                val notatitulo= titulo.text.toString()
                replyIntent.putExtra(EXTRA_TITULO,notatitulo)

                val notadescricao= descricao.text.toString()
                replyIntent.putExtra(EXTRA_DESCRICAO,notadescricao )


                setResult(Activity.RESULT_OK, replyIntent)
                Log.d( "consoleTAG", "Não empty"+titulo.text.toString()+descricao.text.toString())
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_TITULO = "titulo"
        const val EXTRA_DESCRICAO = "descricao"
    }



}