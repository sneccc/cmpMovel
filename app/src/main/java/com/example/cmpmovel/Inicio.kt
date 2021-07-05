package com.example.cmpmovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.cmpmovel.api.EndPoints
import com.example.cmpmovel.api.Login
import com.example.cmpmovel.api.Reporte
import com.example.cmpmovel.api.ServiceBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val PARAM_VARIAVEL ="nome"
class PaginaInicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_inicial)
    }



    fun comecar(view: View) {
        var variavel = "exemplo de variavel"
        val intent=Intent(this,Notas::class.java).apply {
         putExtra(PARAM_VARIAVEL,variavel)
}
        startActivity(intent)
    }

    private lateinit var username: EditText
    private lateinit var password: EditText
    //Login
    fun login(view: View) {

        username = findViewById(R.id.edit_username)
        password = findViewById(R.id.edit_password)

        if(TextUtils.isEmpty(username.text) || TextUtils.isEmpty(password.text) ){
            Toast.makeText(this,"Campos não preenchidos", Toast.LENGTH_SHORT).show()
        }else{


        //Retrofit
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.checkLogin(username.text.toString(),password.text.toString())

        call.enqueue(object : Callback<Login> {

            override fun onResponse(call: Call<Login>, response: Response<Login >) {
                if (response.isSuccessful) {
                   // Log.d("consoleTAG", "Sucesso-> "+response.body()!!)
                    val check = response.body()!!.isVali

                    if(check==1){
                        Log.d("consoleTAG", "Sucesso")
                        Toast.makeText(this@PaginaInicial,"Dados Validados", Toast.LENGTH_LONG).show()

                        val intent=Intent(this@PaginaInicial,MenuLogin::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this@PaginaInicial,"Dados Não Válidos ", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onFailure(call: Call<Login >, t: Throwable) {
                Log.d("consoleTAG", "Falhou-> "+t.message.toString())
            }

        })









        }








    }







}