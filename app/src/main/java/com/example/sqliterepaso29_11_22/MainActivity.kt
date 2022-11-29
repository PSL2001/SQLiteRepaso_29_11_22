package com.example.sqliterepaso29_11_22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliterepaso29_11_22.adapter.LenguajeAdapter
import com.example.sqliterepaso29_11_22.basededatos.BaseDatos
import com.example.sqliterepaso29_11_22.databinding.ActivityMainBinding
import com.example.sqliterepaso29_11_22.modelos.Lenguajes

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: LenguajeAdapter
    lateinit var con: BaseDatos
    var lista = mutableListOf<Lenguajes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        con = BaseDatos(this)
        setLista()
        setRecycler()
        setListeners()
    }

    private fun setListeners() {
        binding.fabAdd.setOnClickListener {
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
        }
    }

    private fun setRecycler() {
        var layoutManager = LinearLayoutManager(this)
        binding.recView.layoutManager = layoutManager
        adapter = LenguajeAdapter(lista) { onDelete(it) }
        binding.recView.adapter = adapter
    }

    fun onDelete(position: Int) {
        val id = lista[position].id as Int
        con.borrar(id)
        lista.removeAt(position)
        adapter.notifyItemRemoved(position)
        setLista()
    }

    private fun setLista() {
        lista = con.read()
        if (lista.size > 0) {
            binding.tvInfo.visibility = View.INVISIBLE
        } else {
            binding.tvInfo.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        setLista()
        setRecycler()
    }
}