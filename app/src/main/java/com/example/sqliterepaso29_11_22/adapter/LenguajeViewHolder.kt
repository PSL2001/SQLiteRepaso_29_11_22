package com.example.sqliterepaso29_11_22.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliterepaso29_11_22.databinding.LayoutLenguajesBinding
import com.example.sqliterepaso29_11_22.modelos.Lenguajes

class LenguajeViewHolder(v: View): RecyclerView.ViewHolder(v) {
    val binding = LayoutLenguajesBinding.bind(v)

    fun render(lenguaje: Lenguajes, onItemDelete: (Int) -> Unit, onInfo: (Lenguajes) -> Unit) {
        binding.tvNombre.text = lenguaje.nombre
        binding.tvDif.text = lenguaje.dificultad
        binding.btnDelete.setOnClickListener {
            onItemDelete(adapterPosition)
        }
        binding.btnUpdate.setOnClickListener {
            onInfo(lenguaje)
        }
    }
}
