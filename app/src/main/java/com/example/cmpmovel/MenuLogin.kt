package com.example.cmpmovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cmpmovel.api.mapa.MapsActivity
import com.example.cmpmovel.api.reportes.Ocorrencia

class MenuLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_login)
    }

    fun List(view: View) {
        var variavel = "exemplo de variavel"
        val intent= Intent(this, Ocorrencia::class.java).apply {
            putExtra(PARAM_VARIAVEL,variavel)
        }
        startActivity(intent)
    }

    fun mapa(view: View) {
        val intent= Intent(this, MapsActivity::class.java)

        startActivity(intent)


    }
}