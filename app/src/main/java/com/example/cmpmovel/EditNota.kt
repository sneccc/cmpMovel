package com.example.cmpmovel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.cmpmovel.viewModel.NotaViewModel
import kotlinx.android.synthetic.main.activity_edit_nota.*


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

            edit_Titulo_Nota.setText(titulo)
            edit_descricao_Nota.setText(descricao)
        }

        //Quando clica-se para guardar os dados editados
        val button = findViewById<Button>(R.id.nota_editar_nota)
        button.setOnClickListener{
            Log.d( "consoleTAG", "Clicou em adicionar")
            //Toast.makeText(this,"EDITOU", Toast.LENGTH_LONG).show()

            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_Titulo_Nota.text)||TextUtils.isEmpty(edit_descricao_Nota.text)) {
                Log.d( "consoleTAG", "Está empty")
                setResult(Activity.RESULT_CANCELED, replyIntent)

                         }else{


                replyIntent.putExtra(Notas.EXTRA_ID,id)
                replyIntent.putExtra(createNota.EXTRA_TITULO,edit_Titulo_Nota.text.toString())
                replyIntent.putExtra(createNota.EXTRA_DESCRICAO,edit_descricao_Nota.text.toString() )
                setResult(Activity.RESULT_OK, replyIntent)

                Log.d( "consoleTAG", "Sucesso! Verificar Variáveis:"+id + edit_Titulo_Nota.text+edit_descricao_Nota.text)

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

    override fun onStart() {
        super.onStart()
        supportActionBar?.setTitle("Editar Nota")
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