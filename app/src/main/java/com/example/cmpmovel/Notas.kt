package com.example.cmpmovel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmpmovel.R
import com.example.cmpmovel.adapter.LineAdapter
import com.example.cmpmovel.dataclasses.Place
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_notas.*

class Notas : AppCompatActivity() {

    private lateinit var myList: ArrayList<Place>
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
    }

    fun insert(view: View) {

        myList.add(0, Place("Country XXX", 999, "Capital XXX"))
        recycler_view.adapter?.notifyDataSetChanged()

    }
}