package com.example.cmpmovel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmpmovel.CellClickListener
import com.example.cmpmovel.R
import com.example.cmpmovel.entities.NotaEnt


class adapter internal constructor(
    context: Context,private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<adapter.NotaViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notaEnt = emptyList<NotaEnt>() // Cached copy of cities

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notaTitle: TextView = itemView.findViewById(R.id.titulo)
        val notaDescricao: TextView = itemView.findViewById(R.id.descricao)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerline, parent, false)
        return NotaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val current = notaEnt[position]

        holder.notaTitle.text =current.titulo
        holder.notaDescricao.text =current.descricao

        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(current)
        }

    }

    internal fun setNotas(notaEnts: List<NotaEnt>) {
        this.notaEnt = notaEnts
        notifyDataSetChanged()
    }

    override fun getItemCount() = notaEnt.size
}