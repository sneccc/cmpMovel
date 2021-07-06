package com.example.cmpmovel.api.reportes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmpmovel.*
import com.example.cmpmovel.adapter.OnReportListener
import com.example.cmpmovel.adapter.reportAdapter
import com.example.cmpmovel.api.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Ocorrencia : AppCompatActivity() ,OnReportListener{
    private val createReportCode = 1
    private val editReportCode = 2
    private val eliminarResponseCode=3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocorrencia)


        val request = ServiceBuilder.buildService(
            EndPoints::class.java
        )//Instancia do  serviço , que permite fazer chamadas http, que está no servicebuilder
        val call = request.getReportes()//Chamar o getReportes situado nos Endpoints
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_oco)

        call.enqueue(object : Callback<List<Reporte>> {//Queue de pedidos
            override fun onResponse(call: Call<List<Reporte>>, response: Response<List<Reporte>>) {//OnResponse se tudo correr bem
                if (response.isSuccessful) {
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@Ocorrencia)
                        adapter = reportAdapter(response.body()!!,this@Ocorrencia)//response.body é o array que recebemos como output do pedido e manda para o adapter para preencher o recycler view

                    }
                }
            }

            override fun onFailure(call: Call<List<Reporte>>, t: Throwable) {//Se Falhar
                Toast.makeText(this@Ocorrencia, "${t.message}", Toast.LENGTH_LONG).show()
                Log.d("consoleTAG", t.message)
            }
        })


/*
        //Adicionar Reporte
        val adicionar = findViewById<Button>(R.id.adicionarReporte)
        adicionar.setOnClickListener {
            val intent = Intent(this@Ocorrencia, CriarReport::class.java)
            startActivityForResult(intent, createReportCode)
        }
*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_oco)

        Log.d( "consoleTAG", "Request Code ->$requestCode")
        if (requestCode == createReportCode) {
            if (resultCode == Activity.RESULT_OK) {
                val title = data?.getStringExtra(createNota.EXTRA_TITULO)
                val descricao = data?.getStringExtra(createNota.EXTRA_DESCRICAO)
                val id = data?.getStringExtra(createNota.EXTRA_ID)

                if (title != null && descricao != null ) {//&&id !=null

                    //Retrofit
                    val request =ServiceBuilder.buildService(EndPoints::class.java)//Instancia do nosso serviço , que permite fazer chamadas http
                    val call = request.postTest(title,descricao)//Chamar o endpoint postTest com parametros

                    call.enqueue(object : Callback<Reporte> {

                        override fun onResponse(call: Call<Reporte>, response: Response<Reporte>) {
                            if (response.isSuccessful) {
                                Log.d("consoleTAG", "Sucesso-> "+response)
                            }
                        }

                        override fun onFailure(call: Call<Reporte>, t: Throwable) {
                            Log.d("consoleTAG", "Falhou-> "+t.message.toString())
                        }

                    })


                }
            }


        }else if(requestCode==editReportCode){

            if (resultCode == Activity.RESULT_OK) {
                val id = data!!.getIntExtra(EditReport.EXTRA_ID,0)
                val title = data.getStringExtra(EditReport.EXTRA_TITULO)
                val descricao = data.getStringExtra(EditReport.EXTRA_DESCRICAO)
                if (title != null && descricao != null) {
                    Log.d("consoleTAG", "Verificar Variáveis na chegada ->ID :"+id+" titulo :"+title+"descrição :"+descricao)
                    //Retrofit
                    val request =
                        ServiceBuilder.buildService(
                            EndPoints::class.java
                        )//Instancia do nosso serviço , que permite fazer chamadas http
                    val call = request.editReporte(id,descricao,title)

                    call.enqueue(object : Callback<Reporte> {
                        override fun onResponse(call: Call<Reporte>, response: Response<Reporte>) {

                            if (response.isSuccessful) {
                                Log.d("consoleTAG", "Sucesso")

                            }
                        }

                        override fun onFailure(call: Call<Reporte>, t: Throwable) {
                            Toast.makeText(this@Ocorrencia, "${t.message}", Toast.LENGTH_LONG).show()
                            Log.d("consoleTAG", "Falhou editar->" + t.message.toString() +call.toString()+t.stackTrace)
                        }

                    })

                }

            }else if(resultCode==eliminarResponseCode){
                val id = data!!.getIntExtra(EditReport.EXTRA_ID,0)
                if (id!=null){
                    val request =
                        ServiceBuilder.buildService(
                            EndPoints::class.java
                        )//Instancia do nosso serviço , que permite fazer chamadas http
                    val call = request.deleteReporte(id)

                    call.enqueue(object : Callback<Reporte> {
                        override fun onResponse(call: Call<Reporte>, response: Response<Reporte>) {

                            if (response.isSuccessful) {
                                Log.d("consoleTAG", "Sucesso DELETE")

                            }
                        }

                        override fun onFailure(call: Call<Reporte>, t: Throwable) {
                           Toast.makeText(this@Ocorrencia, "${t.message}", Toast.LENGTH_LONG).show()
                            Log.d("consoleTAG", "Falhou ELIMINAR->" + t.message)
                        }

                    })
                }
            }


        }

        val intent= Intent(this, Ocorrencia::class.java)
          startActivity(intent)
    }



    companion object {
        const val EXTRA_TITULO = "titulo"
        const val EXTRA_DESCRICAO = "descricao"
        const val EXTRA_ID = "id"
        //const val EXTRA_DATA="data"
        const val EXTRA_ERRO="erro"
    }

    //Quando clicamos em um item do recycle view abre uma nova activity com os dados daquele item em especifico
    override fun onReportClick(report: Reporte, position: Int) {

        val id =report.id
        val titulo =report.titulo
        val descricao =report.descricao
        val intent =Intent(this, EditReport::class.java).apply {
            putExtra(EXTRA_ID,id)
            putExtra(EXTRA_TITULO,titulo)
            putExtra(EXTRA_DESCRICAO,descricao)
        }
        startActivityForResult(intent,editReportCode)
    }

    fun CriarReporte(view: View) {


        //Adicionar Reporte
       // val adicionar = findViewById<Button>(R.id.adicionarReporte)
        //adicionar.setOnClickListener {
            val intent = Intent(this@Ocorrencia, CriarReport::class.java)
            startActivityForResult(intent, createReportCode)
        //}
    }

    fun voltarButtonMenu(view: View) {

        val intent = Intent(this@Ocorrencia, MenuLogin::class.java)
        startActivity(intent)


    }
    // Toast.makeText(this,report.id.toString(),Toast.LENGTH_SHORT).show()


}