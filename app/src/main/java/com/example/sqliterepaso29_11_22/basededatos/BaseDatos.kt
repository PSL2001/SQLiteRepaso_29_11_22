package com.example.sqliterepaso29_11_22.basededatos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqliterepaso29_11_22.modelos.Lenguajes

class BaseDatos(c: Context): SQLiteOpenHelper(c, DB, null, VERSION) {
    companion object {
        const val DB = "academia"
        const val  VERSION = 1
        const val TABLA = "lenguajes"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val q = "CREATE TABLE $TABLA(" +
                "id integer primary key autoincrement, " +
                "nombre text not null unique, " +
                "dificultad text not null)"

        p0?.execSQL(q);

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //Borrar antigua tabla y recrearla
    }

    //Metodos para gestionar la base de datos (CRUD)
    fun create(lenguaje: Lenguajes): Long {
        val con = this.writableDatabase
        val valores = ContentValues().apply {
            put("nombre", lenguaje.nombre)
            put("dificultad", lenguaje.dificultad)
        }
        //Ins devuelve -1 si ha habido errores
        val ins = con.insert(TABLA, null, valores)
        con.close()
        return ins
    }
    //Funcion de leer
    fun read(): MutableList<Lenguajes> {
        val lista = mutableListOf<Lenguajes>()
        val q = "SELECT * FROM $TABLA order by dificultad"
        val con = this.readableDatabase
        try {
            val cursor = con.rawQuery(q, null)
            if (cursor.moveToFirst()) {
                do {
                    val lenguajes = Lenguajes(
                        cursor.getInt(0), //cursor.getColumnIndex("id")
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                    lista.add(lenguajes)
                }while (cursor.moveToNext())
            }
            cursor.close()
        }catch (e: Exception) {
            e.printStackTrace()
        }
        con.close()
        return lista
    }
}