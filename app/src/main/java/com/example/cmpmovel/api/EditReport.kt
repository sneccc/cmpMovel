package com.example.cmpmovel.api

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.cmpmovel.EditNota
import com.example.cmpmovel.Notas
import com.example.cmpmovel.R
import com.example.cmpmovel.createNota
import com.example.cmpmovel.viewModel.NotaViewModel
import kotlinx.android.synthetic.main.activity_edit_nota.*

class EditReport : AppCompatActivity() {
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_report)

        var id = 0
        var titulo = "titulo"
        var descricao = "descricao"
        val extras = intent.extras
        //notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)

        if (extras != null) {

            id= extras.getInt(Ocorrencia.EXTRA_ID)
            titulo = extras.getString(Ocorrencia.EXTRA_TITULO)
            descricao = extras.getString(Ocorrencia.EXTRA_DESCRICAO)

            edit_Titulo_Nota.setText(titulo)
            edit_descricao_Nota.setText(descricao)
        }

        //Quando clica-se para guardar os dados editados
        val editar = findViewById<Button>(R.id.editar_report)
        editar.setOnClickListener{
            Log.d( "consoleTAG", "Clicou em Editar")
            //Toast.makeText(this,"EDITOU", Toast.LENGTH_LONG).show()

            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_Titulo_Nota.text)|| TextUtils.isEmpty(edit_descricao_Nota.text)) {
                Log.d( "consoleTAG", "Está empty")
                setResult(Activity.RESULT_CANCELED, replyIntent)

            }else{


                replyIntent.putExtra(EditReport.EXTRA_ID,id)
                replyIntent.putExtra(EditReport.EXTRA_TITULO,edit_Titulo_Nota.text.toString())
                replyIntent.putExtra(EditReport.EXTRA_DESCRICAO,edit_descricao_Nota.text.toString() )
                setResult(Activity.RESULT_OK, replyIntent)

                Log.d( "consoleTAG", "Sucesso! Verificar Variáveis:" + id+edit_Titulo_Nota.text+edit_descricao_Nota.text)

            }

            finish()
        }


        var eliminar = findViewById<Button>(R.id.eliminar_report)
        eliminar.setOnClickListener{
            Log.d( "consoleTAG", "Clicou em Eliminar")
            //Toast.makeText(this,"EDITOU", Toast.LENGTH_LONG).show()

            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_Titulo_Nota.text)|| TextUtils.isEmpty(edit_descricao_Nota.text)||TextUtils.isEmpty(id.toString())) {
                Log.d( "consoleTAG", "Está empty")
                setResult(Activity.RESULT_CANCELED, replyIntent)

            }else{




                replyIntent.putExtra(EditReport.EXTRA_ID,id)

                setResult(3, replyIntent)

                Log.d( "consoleTAG", "Sucesso! Verificar Variáveis:" + id+edit_Titulo_Nota.text+edit_descricao_Nota.text)

            }

            finish()
}
    }


    override fun onStart() {
        super.onStart()
        supportActionBar?.setTitle("Editar Reporte")
    }

    companion object {
        const val EXTRA_TITULO = "titulo"
        const val EXTRA_DESCRICAO = "descricao"
        const val EXTRA_ID = "id_extra"
    }
}
