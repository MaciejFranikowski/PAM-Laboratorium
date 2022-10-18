package com.example.laba2_franikowski

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var receiptText:EditText
    private lateinit var tipText:EditText
    private lateinit var ratingBarService:RatingBar
    private lateinit var ratingBarFood:RatingBar
    private lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiptText = findViewById(R.id.receiptText)
        tipText = findViewById(R.id.tipText)
        ratingBarService = findViewById(R.id.ratingBarService)
        ratingBarFood = findViewById(R.id.ratingBarFood)
        textView = findViewById(R.id.resultTextView)
    }

    fun calculateReceipt(view: View?) {
        if (inputIsNotEmpty() && validateInput()) {
            var receiptValue = receiptText.text.toString().toDouble()
            val tipValue = tipText.text.toString().toDouble() / 100
            val tipPercentage = calculateTipPercentage(ratingBarService, ratingBarFood)
            receiptValue += (tipPercentage * tipValue) * receiptValue
            textView.text = "$receiptValue zł"
        } else {
            textView.text = ""
        }
    }

    private fun calculateTipPercentage(barService:RatingBar, barFood:RatingBar):Double {
        val serviceVal = barService.rating.toDouble()
        val foodVal = barFood.rating.toDouble()
        val serviceStars = barService.numStars
        val foodStars = barFood.numStars
        var foodPerc = 0.0
        var servicePerc = 0.0
        if(foodStars != 0) foodPerc = foodVal/foodStars
        if(foodStars != 0) servicePerc = serviceVal/serviceStars
        return (foodPerc + servicePerc)/ 2
    }

    private fun inputIsNotEmpty():Boolean{
        var b=true
        if(receiptText.text.toString().trim().isEmpty()){
            receiptText.error="Required"
            receiptText.requestFocus()
            b=false
        }
        if(tipText.text.toString().trim().isEmpty()){
            tipText.error="Required"
            tipText.requestFocus()
            b=false
        }
        return  b
    }

    private fun validateInput():Boolean{
        var b=true
        if(receiptText.text.toString().toDouble() < 0){
            receiptText.error="Invalid input"
            receiptText.requestFocus()
            b=false
        }
        if(tipText.text.toString().toDouble() < 0){
            tipText.error="Invalid input"
            tipText.requestFocus()
            b=false
        }
        return  b
    }
}