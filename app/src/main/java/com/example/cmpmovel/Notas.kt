package com.example.cmpmovel

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmpmovel.adapter.LineAdapter
import com.example.cmpmovel.dataclasses.Place
import kotlinx.android.synthetic.main.activity_notas.*

class Notas : AppCompatActivity() {

     lateinit var myList: ArrayList<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)



        myList = ArrayList<Place>()

        for (i in 0 until 10) {
            myList.add(Place("Country $i", i*500, "Capital $i"))
        }


        recycler_view.adapter = LineAdapter(myList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        //recycler_view.setHasFixedSize(true)
        val texto = intent.getStringExtra("texto")
        val descricao =  intent.getStringExtra("descricao")
        if(!(texto==null||descricao==null)){
            myList.add(0, Place(texto, 999, descricao))
            recycler_view.adapter?.notifyDataSetChanged()
        }
    }

    override fun onStart() {
        super.onStart()

        supportActionBar?.setTitle("As minhas notas")


    }

    fun  insert(view: View) {

        myList.add(0, Place("Country XXX", 999, "Capital XXX"))
        recycler_view.adapter?.notifyDataSetChanged()

    }



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
}