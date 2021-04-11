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
private lateinit var notaViewModel: NotaViewModel

class EditNota : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_nota)


        var id= 0
        var titulo ="Nota não selecionada"
        var descricao ="Nota não selecionada"
        var extras = intent.extras

        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)

        if (extras != null) {
            id = extras.getInt("id")
            titulo = extras.getString("titulo")
            descricao = extras.getString("descricao")
            editTitulo.setText(titulo)
            editDescricao.setText(descricao)
        }
        val button = findViewById<Button>(R.id.nota_editar)
        button.setOnClickListener{
            Toast.makeText(this,"EDITOU", Toast.LENGTH_LONG)
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTitulo.text)&&TextUtils.isEmpty(editDescricao.text)) {

                setResult(Activity.RESULT_CANCELED, replyIntent)
                Log.d( "consoleTAG", "Está empty")
            }else{


                replyIntent.putExtra(Notas.EXTRA_ID,id)
                replyIntent.putExtra(createNota.EXTRA_TITULO,titulo)
                replyIntent.putExtra(createNota.EXTRA_DESCRICAO,descricao )
                setResult(Activity.RESULT_OK, replyIntent)
                Log.d( "consoleTAG", "Verificar Variáveis: " +id+" "+titulo+" "+descricao)

            }

            finish()
        }



    }



    companion object {
        const val EXTRA_TITULO = "titulo"
        const val EXTRA_DESCRICAO = "descricao"
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