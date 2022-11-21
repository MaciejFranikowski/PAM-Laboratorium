package com.example.laba6_franikowski.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.laba6_franikowski.model.Photo

class DataManager(context: Context) {
    private val db : SQLiteDatabase = context.openOrCreateDatabase("Photos", Context.MODE_PRIVATE, null)
    init {
        val photosCreateQuery = "CREATE TABLE IF NOT EXISTS `Photos` ( `Id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `Title` TEXT NOT NULL)"
        db.execSQL(photosCreateQuery)
    }

    fun add(photo: Photo){
        val query = """INSERT INTO Photos (Title) Values ('${photo.title}')"""
        db.execSQL(query)
    }

    fun getAllPhotos():List<Photo>{
        val query: String = "SELECT * FROM Photos";
        return photos(query, null)
    }

    private fun photos(query:String, args: Array<String>?):List<Photo>{
        val photos = mutableListOf<Photo>()

        val cursor = db.rawQuery(query, args)
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("Id"))
                val title = cursor.getString(cursor.getColumnIndexOrThrow("Title"))
                val photo = Photo(id, title)
                photos.add(photo)
            }while(cursor.moveToNext())
        }
        cursor.close()
        return photos

    }
}