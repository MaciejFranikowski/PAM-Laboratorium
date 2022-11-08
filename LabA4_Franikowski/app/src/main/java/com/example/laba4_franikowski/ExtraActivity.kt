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
        val myCallerIntent = intent
        val myBundle = myCallerIntent.extras
        val index = myBundle?.getString("index")
        if(index != null){
            textView.text = index
        }
        if(index == "249436"){
            myBundle?.putString("Result", "Maciej Franikowski, 5.0")
        } else {
            myBundle?.putString("Result", "Student nie rozpoznany")
        }
        if (myBundle != null) {
            myCallerIntent.putExtras(myBundle)
        }
        setResult(Activity.RESULT_OK, myCallerIntent)
    }

    fun finish(view: View) {
        finish()
    }
}