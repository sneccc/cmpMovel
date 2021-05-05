package com.example.cmpmovel.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmpmovel.R
import com.example.cmpmovel.adapter.userAdapter
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Ocorrencia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocorrencia)

        val request = ServiceBuilder.buildService(EndPoints::class.java)//Instancia do nosso serviço , que permite fazer chamadas http
        val call = request.getUsers()//Chamar o endpoint getusers
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_oco)

        call.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>){
if (response.isSuccessful){
    recyclerView.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this@Ocorrencia)
        adapter=userAdapter(response.body()!!)
    }
}
            }

            override fun onFailure(call: Call<List<User>>,t:Throwable){
                Toast.makeText(this@Ocorrencia,"${t.message}",Toast.LENGTH_SHORT).show()
            }
        })


    }


}