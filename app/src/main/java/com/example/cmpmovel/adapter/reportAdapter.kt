package com.example.cmpmovel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmpmovel.R
import com.example.cmpmovel.api.Reporte


class reportAdapter(val reports:List<Reporte>,var clicklistener:OnReportListener):RecyclerView.Adapter<ReportsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.recyclerline_reportes,parent,false)
        return  ReportsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  reports.size
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) {
        holder.initialize(reports.get(position),clicklistener)
       // return holder.bind(reports[position])
    }
}

class ReportsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    private val titulo:TextView=itemView.findViewById(R.id.tituloOco)
    private val desc:TextView=itemView.findViewById(R.id.descricaoOco)
    private val id:TextView=itemView.findViewById(R.id.textViewid)
   /* fun bind(report:Reporte){
        id.text=report.id.toString()
        titulo.text=report.titulo
        desc.text=report.descricao
    }
*/
    fun  initialize(report: Reporte,action:OnReportListener){
        id.text=report.id.toString()
        titulo.text=report.titulo
        desc.text=report.descricao

        itemView.setOnClickListener{
            action.onReportClick(report,adapterPosition)
        }

}
}

 interface  OnReportListener{
     fun onReportClick(report: Reporte,position: Int);
}