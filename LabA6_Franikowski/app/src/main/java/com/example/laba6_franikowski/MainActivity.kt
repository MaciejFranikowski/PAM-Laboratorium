package com.example.laba6_franikowski

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.laba6_franikowski.adapter.ItemAdapter
import com.example.laba6_franikowski.database.DataManager
import com.example.laba6_franikowski.model.Faculty

class MainActivity : AppCompatActivity() {
    private lateinit var dataManager: DataManager
    private lateinit var dataSet: List<Faculty>
    private lateinit var recyclerView: RecyclerView
    private lateinit var textMain: EditText
    private lateinit var textFind: EditText
    private lateinit var textUpdate: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textMain = findViewById(R.id.editText)
        textFind = findViewById(R.id.EditTextFind)
        textUpdate = findViewById(R.id.EditTextFind)

//        dataManager = DataManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

//        refreshFaculties()
    }

    fun addItem(view: View?){
        val title = textMain.text.toString()
        val faculty = Faculty(null, title)
        dataManager.add(faculty)
        refreshFaculties()
        textMain.setText("")
    }



    fun findFaculty(view: View) {
        val faculty = dataManager.findFaculty(textMain.text.toString())
        if (faculty.isNotEmpty() && faculty[0] != null) {
            textFind.setText(faculty[0].title)
        } else {
            textFind.setText("No faculty found")
        }
    }

    fun deleteItem(view: View?){
        val faculty = dataManager.findFaculty(textFind.text.toString())
        if (faculty.isNotEmpty() && faculty[0] != null) {
            dataManager.delete(faculty[0])
        } else {
            textFind.setText("No faculty found")
        }
        refreshFaculties()
    }

    fun editItem(view: View?){
        val faculty = dataManager.findFaculty(textFind.text.toString())
        if (faculty.isNotEmpty() && faculty[0] != null) {
            val newFaculty = Faculty(faculty[0].id, textUpdate.text.toString())
            dataManager.edit(newFaculty)
        } else {
            textFind.setText("No faculty found")
        }
        refreshFaculties()
    }

    private fun refreshFaculties(){
        dataSet = dataManager.getAllFaculties()
        recyclerView.adapter = ItemAdapter(this, dataSet)
    }
}