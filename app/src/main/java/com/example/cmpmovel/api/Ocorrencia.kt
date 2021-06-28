package com.example.cmpmovel.api

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmpmovel.R
import com.example.cmpmovel.adapter.reportAdapter
import com.example.cmpmovel.createNota
import com.example.cmpmovel.entities.NotaEnt

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Ocorrencia : AppCompatActivity() {
    private val createReportCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocorrencia)

        val request = ServiceBuilder.buildService(EndPoints::class.java)//Instancia do  serviço , que permite fazer chamadas http, que está no servicebuilder
        val call = request.getReportes()//Chamar o getReportes situado nos Endpoints
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_oco)

        call.enqueue(object : Callback<List<Reporte>> {//Queue de pedidos
            override fun onResponse(call: Call<List<Reporte>>, response: Response<List<Reporte>>) {//OnResponse se tudo correr bem
                if (response.isSuccessful) {
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@Ocorrencia)
                        adapter = reportAdapter(response.body()!!)//response.body é o array que recebemos como output do pedido e manda para o adapter para preencher o recycler view

                    }
                }
            }

            override fun onFailure(call: Call<List<Reporte>>, t: Throwable) {//Se Falhar
                Toast.makeText(this@Ocorrencia, "${t.message}", Toast.LENGTH_LONG).show()
                Log.d("consoleTAG", t.message)
            }
        })



        //Adicionar Reporte
        val adicionar = findViewById<Button>(R.id.adicionarReporte)
        adicionar.setOnClickListener {
            val intent = Intent(this@Ocorrencia, editReport::class.java)
            startActivityForResult(intent, createReportCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == createReportCode) {
            if (resultCode == Activity.RESULT_OK) {
                val title = data?.getStringExtra(createNota.EXTRA_TITULO)
                val descricao = data?.getStringExtra(createNota.EXTRA_DESCRICAO)
                val id = data?.getStringExtra(createNota.EXTRA_ID)

                if (title != null && descricao != null ) {//&&id !=null

                    //Retrofit
                    val request = ServiceBuilder.buildService(EndPoints::class.java)//Instancia do nosso serviço , que permite fazer chamadas http
                    val call = request.postTest("retro","retro",8)//Chamar o endpoint postTest com parametros

                    call.enqueue(object : Callback<Reporte> {

                        override fun onResponse(call: Call<Reporte>, response: Response<Reporte>) {
                            if (response.isSuccessful) {
                            val c:Reporte=response.body()!!
                                Toast.makeText(this@Ocorrencia, "Responsta sem falha"+c.id.toString()+"-"+c.titulo+"-"+c.descricao, Toast.LENGTH_LONG)
                                    .show()

                            }
                        }

                        override fun onFailure(call: Call<Reporte>, t: Throwable) {
                            Toast.makeText(this@Ocorrencia, "${t.message}", Toast.LENGTH_LONG)
                                .show()
                            Log.d("consoleTAG", t.message)
                        }

                    })


                }
            }


        }

    }


}