package com.example.laba1_franikowski

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val duration = Toast.LENGTH_SHORT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "onCreate", duration).show()
        val buttonExit = findViewById<Button>(R.id.buttonExit)
        buttonExit.setOnClickListener{
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", duration).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart", duration).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", duration).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", duration).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", duration).show()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Toast.makeText(this, "onSaveInstanceState", duration).show()
    }
}