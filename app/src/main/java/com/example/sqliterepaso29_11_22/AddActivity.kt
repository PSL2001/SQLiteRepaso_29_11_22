package com.example.sqliterepaso29_11_22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.sqliterepaso29_11_22.R
import com.example.sqliterepaso29_11_22.basededatos.BaseDatos
import com.example.sqliterepaso29_11_22.databinding.ActivityAddBinding
import com.example.sqliterepaso29_11_22.modelos.Lenguajes

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    lateinit var con: BaseDatos
    var nombre = ""
    var dificultad = "Básico"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        con = BaseDatos(this)
        setListeners()
    }

    private fun setListeners() {
        binding.btnVolver.setOnClickListener {
            finish()
        }
        binding.btnGuardar.setOnClickListener {
            guardar()
        }
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                dificultad = binding.spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun guardar() {
        nombre = binding.etNombre.text.toString().trim().uppercase()
        if (nombre.isEmpty()) {
            binding.etNombre.setError("Rellena este campo!")
            binding.etNombre.requestFocus()
            return
        }

        val lenguaje = Lenguajes(0, nombre, dificultad)
        if (con.create(lenguaje) < 0) {
            Toast.makeText(this, "No se pudo guardar el registro", Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }

    }
}