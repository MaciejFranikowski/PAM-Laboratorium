package com.example.laba4_franikowski

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ExtraActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra)
        val textView: TextView = findViewById(R.id.textView)

        val textView2: TextView = findViewById(R.id.textView2)
        val myCallerIntent = intent
        val myBundle = myCallerIntent.extras
        val longitude = myBundle?.getString("longitude")
        val latitude = myBundle?.getString("latitude")
        if(latitude != null && longitude != null){
            textView.text = latitude
            textView2.text = longitude
        }
        myBundle?.putString("Result", latitude + latitude)
        if (myBundle != null) {
            myCallerIntent.putExtras(myBundle)
        }
        setResult(Activity.RESULT_OK, myCallerIntent)
    }

    fun finish(view: View) {
        finish()
    }
}