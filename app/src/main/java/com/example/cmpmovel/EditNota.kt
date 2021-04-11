package com.example.cmpmovel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cmpmovel.viewModel.NotaViewModel
import kotlinx.android.synthetic.main.activity_edit_nota.*
import kotlinx.android.synthetic.main.recyclerline.*


class EditNota : AppCompatActivity() {
    private lateinit var notaViewModel: NotaViewModel

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nota)

        var id = "id"
        var titulo = "titulo"
        var descricao = "descricao"


        val extras = intent.extras

        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)

        if (extras != null) {

            id= extras.getString(EXTRA_ID)
            titulo = extras.getString(EXTRA_TITULO)
            descricao = extras.getString(EXTRA_DESCRICAO)

            editTitulo.setText(titulo)
            editDescricao.setText(descricao)
        }

        //Quando clica-se para guardar os dados editados
        val button = findViewById<Button>(R.id.nota_editar)
        button.setOnClickListener{

            Toast.makeText(this,"EDITOU", Toast.LENGTH_LONG).show()

            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTitulo.text)&&TextUtils.isEmpty(editDescricao.text)&&TextUtils.isEmpty(id)) {

                setResult(Activity.RESULT_CANCELED, replyIntent)
                Log.d( "consoleTAG", "Está empty")
                         }else{


                replyIntent.putExtra(Notas.EXTRA_ID,id)
                replyIntent.putExtra(createNota.EXTRA_TITULO,editTitulo.text.toString())
                replyIntent.putExtra(createNota.EXTRA_DESCRICAO,editDescricao.text.toString() )
                setResult(Activity.RESULT_OK, replyIntent)

                Log.d( "consoleTAG", "Verificar Variáveis:"+id + editTitulo.text+editDescricao.text)

            }

            finish()
        }


        //Quando clica no botão eliminar

        val button2 = findViewById<Button>(R.id.nota_eliminar)
        button2.setOnClickListener{
            Log.d( "consoleTAG", "Botão delete")
           // intent?.setAction("DELETE")
            val replyIntent2 = Intent()
            replyIntent2.action = "DELETE"
            replyIntent2.putExtra(Notas.EXTRA_ID,id)//id:String
            setResult(Activity.RESULT_OK, replyIntent2)

            finish()
        }



    }



    companion object {
        const val EXTRA_TITULO = "titulo"
        const val EXTRA_DESCRICAO = "descricao"
        const val EXTRA_ID = "id"
    }
}





/*
*     }

    fun onclickeditar(view: View) {
if (TextUtils.isEmpty(editTitulo.text)&&TextUtils.isEmpty(editDescricao.text)){
    val intent = Intent(this,Notas::class.java)
    startActivity(intent)
    Log.d( "consoleTAG", "editou")

}else{
    notaViewModel.update(id = id,titulo = titulo,descricao = descricao)
    finish()
    Log.d( "consoleTAG", "update")

}

    }*/