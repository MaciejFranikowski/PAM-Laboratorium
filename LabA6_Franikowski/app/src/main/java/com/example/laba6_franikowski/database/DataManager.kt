package com.example.laba6_franikowski.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.laba6_franikowski.model.Faculty

class DataManager(context: Context) {
    private val db : SQLiteDatabase = context.openOrCreateDatabase("Faculties", Context.MODE_PRIVATE, null)
    init {
        val facultiesCreateQuery = "CREATE TABLE IF NOT EXISTS `Faculties` ( `Id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `Title` TEXT NOT NULL)"
        db.execSQL(facultiesCreateQuery)
    }

    fun add(faculty: Faculty){
        val query = """INSERT INTO Faculties (Title) Values ('${faculty.title}')"""
        db.execSQL(query)
    }

    fun getAllFaculties():List<Faculty>{
        val query: String = "SELECT * FROM Faculties";
        return faculties(query, null)
    }

    fun delete(faculty: Faculty){
        if(faculty.id != null){
            val query = """DELETE FROM Faculties WHERE `Id` == ('${faculty.id}')"""
            db.execSQL(query)
        }
    }

    fun edit(faculty: Faculty){
        if(faculty.id != null){
            val query = """UPDATE Faculties SET `Title` = '${faculty.title}' WHERE `Id` == ('${faculty.id}')"""
            db.execSQL(query)
        } else {
            val query = """INSERT INTO Faculties (Title) Values ('${faculty.title}')"""
            db.execSQL(query)
        }
    }

    fun findFaculty(title: String): List<Faculty> {
        val query = """SELECT * FROM Faculties WHERE `Title` ==  '${title}'"""
        return faculties(query, null)
    }

    private fun faculties(query:String, args: Array<String>?):List<Faculty>{
        val faculties = mutableListOf<Faculty>()

        val cursor = db.rawQuery(query, args)
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("Id"))
                val title = cursor.getString(cursor.getColumnIndexOrThrow("Title"))
                val faculty = Faculty(id, title)
                faculties.add(faculty)
            }while(cursor.moveToNext())
        }
        cursor.close()
        return faculties
    }
}