package com.example.cmpmovel.api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmpmovel.R
import com.example.cmpmovel.adapter.reportAdapter
import com.example.cmpmovel.createNota
import com.google.android.material.floatingactionbutton.FloatingActionButton

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Ocorrencia : AppCompatActivity() {
    private val reportCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocorrencia)

        val request = ServiceBuilder.buildService(EndPoints::class.java)//Instancia do nosso serviço , que permite fazer chamadas http
        val call = request.getReportes()//Chamar o endpoint getReport
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_oco)
        call.enqueue(object : Callback<List<Reporte>> {
            override fun onResponse(call: Call<List<Reporte>>, response: Response<List<Reporte>>) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@Ocorrencia)
                        adapter = reportAdapter(response.body()!!)

                    }
                }
            }

            override fun onFailure(call: Call<List<Reporte>>, t: Throwable) {
                Toast.makeText(this@Ocorrencia, "${t.message}", Toast.LENGTH_LONG).show()
                Log.d("consoleTAG", t.message)
            }
        })



        //Adicionar Reporte
        val adicionar = findViewById<Button>(R.id.adicionarReporte)
        adicionar.setOnClickListener {
            val intent = Intent(this@Ocorrencia, editReport::class.java)
            startActivityForResult(intent, reportCode)
        }

    }


}