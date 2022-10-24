package com.example.laba3_franikowski

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var operation: String
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewResult = findViewById(R.id.textViewResult)
    }

    fun buttonPressed(view: View){
        if(view.id == R.id.buttonClear) {
            operation = ""
            textViewResult.text = ""
            return
        }
        if(isOperationButton(view) && (isOperationAlreadyPresent(textViewResult) || textViewResult.text.isEmpty()))
            return

        if(view.id == R.id.buttonCalculate){
            calculate(textViewResult)
            return
        }


        val button: Button = view as Button
        textViewResult.text = textViewResult.text.toString().plus(button.text)
    }


    private fun isOperationButton(view:View): Boolean {
        val buttons:List<Int> = mutableListOf(
            R.id.buttonPlus, R.id.buttonMinus,
            R.id.buttonMultiply, R.id.buttonDivide
        )
        return buttons.contains(view.id)
    }

    private fun isOperationAlreadyPresent(textViewResult:TextView): Boolean {
        return  textViewResult.text.toString().contains("+") ||
                textViewResult.text.toString().contains("*") ||
                textViewResult.text.toString().contains("/") ||
                textViewResult.text.toString().contains("-")
    }

    private fun calculate(textViewResult:TextView){
        val index : Int = textViewResult.text.indexOfAny(charArrayOf('+', '-', '*', '/'))
        Log.i("index", index.toString())
        val firstNumber: Int = textViewResult.text.subSequence(0, index).toString().toInt()
        val secondNumber: Int = textViewResult.text.subSequence(index+1, textViewResult.text.length).toString().toInt()

        Log.i("firstNum", firstNumber.toString())
        Log.i("secondNum", secondNumber.toString())
        Log.i("secondNum", textViewResult.text[index].toString())
        when (textViewResult.text[index]) {
            '+' -> textViewResult.text = (firstNumber + secondNumber).toString()
            '-' -> textViewResult.text = (firstNumber - secondNumber).toString()
            '*' -> textViewResult.text = (firstNumber * secondNumber).toString()
            '/' -> textViewResult.text = (firstNumber / secondNumber).toString()
            else -> {
                System.out.printf("Error! operator is not correct")
                return
            }
        }
    }
}