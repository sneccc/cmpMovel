package com.example.cmpmovel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmpmovel.R
import com.example.cmpmovel.api.User

class userAdapter(val users:List<User>):RecyclerView.Adapter<UsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.recyclerline_oco,parent,false)
        return  UsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  users.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        return holder.bind(users[position])
    }
}

class UsersViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    private val name:TextView=itemView.findViewById(R.id.tituloOco)
    private val city:TextView=itemView.findViewById(R.id.descricaoOco)
    fun bind(user:User){
        name.text=user.name
        city.text=user.address.city
    }
}

