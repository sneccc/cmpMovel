package com.example.cmpmovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cmpmovel.api.Ocorrencia

class MenuLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_login)
    }

    fun List(view: View) {
        var variavel = "exemplo de variavel"
        val intent= Intent(this,Ocorrencia::class.java).apply {
            putExtra(PARAM_VARIAVEL,variavel)
        }
        startActivity(intent)
    }
}