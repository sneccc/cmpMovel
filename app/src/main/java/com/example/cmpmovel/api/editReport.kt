package com.example.cmpmovel.api

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cmpmovel.R
import com.example.cmpmovel.createNota

class editReport : AppCompatActivity() {

    private lateinit var titulo: EditText
    private lateinit var descricao: EditText
    private lateinit var id: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_report)


        titulo = findViewById(R.id.editTituloReport)
        descricao = findViewById(R.id.editDescricaoReport)
        id=findViewById(R.id.id_report)

        val adicionar = findViewById<Button>(R.id.nota_adicionar_report)

        adicionar.setOnClickListener {


            val replyIntent = Intent()

            if (TextUtils.isEmpty(titulo.text)||TextUtils.isEmpty(descricao.text)||TextUtils.isEmpty(id.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)

            } else {

                val notatitulo= titulo.text.toString()
                replyIntent.putExtra(createNota.EXTRA_TITULO,notatitulo)

                val notadescricao= descricao.text.toString()
                replyIntent.putExtra(createNota.EXTRA_DESCRICAO,notadescricao )


                setResult(Activity.RESULT_OK, replyIntent)
                Log.d( "consoleTAG", "Report Done"+titulo.text.toString()+descricao.text.toString())
            }
            finish()
        }
    }
}