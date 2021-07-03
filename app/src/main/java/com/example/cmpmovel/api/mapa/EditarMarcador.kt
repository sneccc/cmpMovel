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

class EditarMarcador : AppCompatActivity() {
    private lateinit var titulo: EditText
    private lateinit var descricao: EditText
    private lateinit var lat: EditText
    private lateinit var lon: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_marcador)
        //buscar as views da activity

        titulo = findViewById(R.id.criar_Titulo_Marcador)
        descricao = findViewById(R.id.criar_descricao_Marcador)
        lat = findViewById(R.id.criar_latitude)
        lon = findViewById(R.id.criar_longitude)
        val extras = intent.extras


        if (extras != null) {//Preencher Formulário

            //id = extras.getInt(MapsActivity.EXTRA_ID)
            titulo.setText(extras.getString(MapsActivity.EXTRA_TITULO))
            descricao.setText(extras.getString(MapsActivity.EXTRA_DESCRICAO))
            lat.setText(extras.getString(MapsActivity.EXTRA_LAT))
            lon.setText(extras.getString(MapsActivity.EXTRA_LON))

        }


        val editarMarcador = findViewById<Button>(R.id.editar_marcador)
        editarMarcador.setOnClickListener   {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(titulo.text) || TextUtils.isEmpty(descricao.text) || TextUtils.isEmpty(
                    lat.text
                ) || TextUtils.isEmpty(lon.text)
            ) {

                setResult(Activity.RESULT_CANCELED, replyIntent)
                Log.d("consoleTAG", "Cancelado pq alguns parametros estão vazios")

            } else {

                replyIntent.putExtra(
                    MapsActivity.EXTRA_ID,
                    extras.getInt(MapsActivity.EXTRA_ID).toString()
                )

                val marcadorTitulo = titulo.text.toString()
                replyIntent.putExtra(MapsActivity.EXTRA_TITULO, marcadorTitulo)

                val marcadorDescricao = descricao.text.toString()
                replyIntent.putExtra(MapsActivity.EXTRA_DESCRICAO, marcadorDescricao)

                val marcadorlatitude = lat.text.toString()
                replyIntent.putExtra(MapsActivity.EXTRA_LAT, marcadorlatitude)

                val marcadorlongitude = lon.text.toString()
                replyIntent.putExtra(MapsActivity.EXTRA_LON, marcadorlongitude)

                setResult(Activity.RESULT_OK, replyIntent)
                Log.d(
                    "consoleTAG",
                    "Report Done" + marcadorTitulo + marcadorDescricao + marcadorlatitude + marcadorlongitude
                )
            }
            finish()
        }

        var eliminar = findViewById<Button>(R.id.eliminar_marcador)
        eliminar.setOnClickListener {
            Log.d("consoleTAG", "Clicou em Eliminar")
            //Toast.makeText(this,"EDITOU", Toast.LENGTH_LONG).show()

            val replyIntent = Intent()

            replyIntent.putExtra(
                MapsActivity.EXTRA_ID,
                extras.getInt(MapsActivity.EXTRA_ID).toString()
            )

            setResult(5, replyIntent)

            Log.d(
                "consoleTAG",
                "Deletado Verificar ID->" + extras.getInt(MapsActivity.EXTRA_ID).toString()
            )
            finish()
        }


    }

    override fun onStart() {
        super.onStart()
        supportActionBar?.setTitle("Editar Marcador")
    }


}

