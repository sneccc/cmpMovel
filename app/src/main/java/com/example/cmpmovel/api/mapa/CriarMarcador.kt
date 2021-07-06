package com.example.cmpmovel.api.mapa

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.cmpmovel.R
import com.example.cmpmovel.createNota

class CriarMarcador : AppCompatActivity() {

    private lateinit var titulo: EditText
    private lateinit var descricao: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_marcador)

        titulo = findViewById(R.id.criar_Titulo_Marcador)
        descricao = findViewById(R.id.criar_descricao_Marcador)

        val criarMarcador = findViewById<Button>(R.id.criar_marcador)
        criarMarcador.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(titulo.text)|| TextUtils.isEmpty(descricao.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
                Log.d( "consoleTAG", "Cancelado pq alguns parametros estão vazios")
            } else {

                val notatitulo= titulo.text.toString()
                replyIntent.putExtra(MapsActivity.EXTRA_TITULO,notatitulo)

                val notadescricao= descricao.text.toString()
                replyIntent.putExtra(MapsActivity.EXTRA_DESCRICAO,notadescricao )


                setResult(Activity.RESULT_OK, replyIntent)
                Log.d( "consoleTAG", "Report Done"+titulo.text.toString()+descricao.text.toString())
            }
            finish()
        }
    }
}