package com.example.cmpmovel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.cmpmovel.adapter.LineAdapter
//import com.example.cmpmovel.dataclasses.Place

import android.app.Activity
import android.util.Log


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cmpmovel.adapter.adapter
import com.example.cmpmovel.entities.NotaEnt
import com.example.cmpmovel.viewModel.NotaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
//  notasPessoais
class Notas : AppCompatActivity(), CellClickListener {

    private lateinit var notaViewModel: NotaViewModel
    private val newNotaActivityCode = 1
    private val editNotaActivityCode = 2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = adapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)





        // view model
        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        notaViewModel.allNotes.observe(this, Observer { notaEnt ->
            // Update the cached copy of the words in the adapter.
            notaEnt?.let { adapter.setNotas(it) }
        })


        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@Notas, createNota::class.java)
            startActivityForResult(intent, newNotaActivityCode)
        }


    }

    override fun onStart() {
        super.onStart()
        supportActionBar?.setTitle("As minhas notas")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if (requestCode == newNotaActivityCode ) {
if(resultCode==Activity.RESULT_OK){

    val title = data?.getStringExtra(createNota.EXTRA_TITULO)
    val descricao = data?.getStringExtra(createNota.EXTRA_DESCRICAO)
    if (title!= null && descricao != null) {

        val notaEnt = NotaEnt(titulo = title, descricao = descricao)
        notaViewModel.insert(notaEnt)
    }
} else{
    Toast.makeText(
        applicationContext,"NOTA VAZIA"
        ,
        Toast.LENGTH_LONG).show()
}



        }else if(requestCode==editNotaActivityCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (data?.action == "DELETE") {
                    val id = data?.getStringExtra(EXTRA_TITULO).toString()
                    notaViewModel.delete(Integer.parseInt(id))

                } else {
                    val id = data?.getStringExtra(EXTRA_ID).toString()
                    val titulo = data?.getStringExtra(EXTRA_TITULO).toString()
                    val descricao = data?.getStringExtra(EXTRA_DESCRICAO).toString()

                    notaViewModel.update(Integer.parseInt(id), titulo, descricao)
                }

            } else {
                Log.d("consoleTAG", "Não entrou")
                Toast.makeText(
                    applicationContext, "NO"
                    ,
                    Toast.LENGTH_LONG
                ).show()
            }


        }
    }


    /*Clicar na nota para editar
    override fun onCellClickListener(notaent:NotaEnt){
        val id =notaent.id.toString()

        val titulo =notaent.titulo
        val descricao =notaent.descricao
        //val data =notaent.data
        val intent =Intent(this,EditNota::class.java).apply {
            putExtra(EXTRA_ID,id)
            putExtra(EXTRA_TITULO,titulo)
            putExtra(EXTRA_DESCRICAO,descricao)
           // putExtra(EXTRA_DATA,data)



        }
        startActivityForResult(intent,editNotaActivityCode)
    }

*/

    companion object {
        const val EXTRA_TITULO = "titulo"
        const val EXTRA_DESCRICAO = "descricao"
        const val EXTRA_ID = "id"
        //const val EXTRA_DATA="data"
        const val EXTRA_ERRO="erro"
    }

    override fun onCellClickListener(notaent: NotaEnt) {
        val id =notaent.id.toString()

        val titulo =notaent.titulo
        val descricao =notaent.descricao
        //val data =notaent.data
        val intent =Intent(this,EditNota::class.java).apply {
            putExtra(EXTRA_ID,id)
            putExtra(EXTRA_TITULO,titulo)
            putExtra(EXTRA_DESCRICAO,descricao)
            // putExtra(EXTRA_DATA,data)



        }
        startActivityForResult(intent,editNotaActivityCode)
    }


/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater=menuInflater
        inflater.inflate(R.menu.menu,menu)
        //return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId){
    R.id.adicionar->{//Caso clicke em adicionar
    Toast.makeText(this,"adicionar",Toast.LENGTH_SHORT).show()

    //Mudar para activity de criação de nota
    var variavel = "exemplo de variavel"
    val intent= Intent(this,createNota::class.java).apply {
        putExtra(PARAM_VARIAVEL,variavel)
    }
    startActivity(intent)


    true
}

            else -> super.onOptionsItemSelected(item)
        }
    }

 */
}