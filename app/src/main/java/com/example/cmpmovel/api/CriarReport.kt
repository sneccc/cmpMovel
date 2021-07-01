package com.example.cmpmovel.api

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.cmpmovel.R
import com.example.cmpmovel.createNota

class CriarReport : AppCompatActivity() {

    private lateinit var titulo: EditText
    private lateinit var descricao: EditText
    private lateinit var id: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_report)



        titulo = findViewById(R.id.edit_Titulo_Nota)
        descricao = findViewById(R.id.edit_descricao_Nota)



        val adicionar = findViewById<Button>(R.id.nota_adicionar_report)

        adicionar.setOnClickListener {
            Log.d( "consoleTAG", "Clicou Adicionar!, Variáveis que foi buscar :"+titulo.text+descricao.text)

            val replyIntent = Intent()

            if (TextUtils.isEmpty(titulo.text)||TextUtils.isEmpty(descricao.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
                Log.d( "consoleTAG", "Cancelado pq alguns parametros estão vazios")
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