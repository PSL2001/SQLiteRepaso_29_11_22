package com.example.sqliterepaso29_11_22.basededatos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(c: Context): SQLiteOpenHelper(c, DB, null, VERSION) {
    companion object {
        const val DB = "academia"
        const val  VERSION = 1
        const val TABLA = "lenguajes"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}