package com.example.cmpmovel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Associação da atividade com o  layout (Testar commit)
    }


    override fun onPause() {
        super.onPause()
        Log.d( "consoleTAG", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d( "consoleTAG", "onStop")
    }

    override fun onStart() {
        super.onStart()
        Log.d( "consoleTAG", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d( "consoleTAG", "onResume")
    }

    override fun onDestroy() {
            super.onDestroy()
        Log.d( "consoleTAG", "onDestroy")
    }


    fun adicionar(view: View) {
        var editText = findViewById<EditText>(R.id.textoTitulo)
        Log.d( "consoleTAG", editText.text.toString())



    }
}