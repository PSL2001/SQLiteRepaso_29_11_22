package com.example.sqliterepaso29_11_22.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliterepaso29_11_22.R
import com.example.sqliterepaso29_11_22.modelos.Lenguajes

class LenguajeAdapter(
    private var lista: MutableList<Lenguajes>,
    val onItemDelete: (Int) -> Unit,
    val onInfo: (Lenguajes) -> Unit
): RecyclerView.Adapter<LenguajeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LenguajeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_lenguajes, parent, false)
        return LenguajeViewHolder(v)
    }

    override fun onBindViewHolder(holder: LenguajeViewHolder, position: Int) {
        val lenguaje = lista[position]
        holder.render(lenguaje, onItemDelete, onInfo)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}