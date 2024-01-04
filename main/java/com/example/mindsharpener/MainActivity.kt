package com.example.mindsharpener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kotlin.math.pow
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    private lateinit var levelRadioGroup: RadioGroup
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var radioButton3: RadioButton
    private lateinit var textView4: TextView
    private lateinit var textView5: TextView
    private lateinit var textView6: TextView
    private lateinit var textView7: TextView
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var textview8: TextView
    private lateinit var operatorArray: Array<String>
    private var points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        levelRadioGroup = findViewById(R.id.levelRadioGroup)
        radioButton1 = findViewById(R.id.radiobtn1)
        radioButton2 = findViewById(R.id.radiobtn2)
        radioButton3 = findViewById(R.id.radiobtn3)
        textView4 = findViewById(R.id.textview4)
        textView5 = findViewById(R.id.textview5)
        textView6 = findViewById(R.id.textview6)
        textView7 = findViewById(R.id.textview7)
        editText = findViewById(R.id.edittext)
        button = findViewById(R.id.button)
        textview8 = findViewById(R.id.textview8)
        operatorArray = arrayOf("+", "-", "*", "/")

        setMaxDigitsForLevel("i3")

        levelRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton: RadioButton = findViewById(checkedId)
            val level = selectedRadioButton.text.toString()
            setMaxDigitsForLevel(level)
        }

        button.setOnClickListener {
            checkAnswer()
        }
    }

    private fun setMaxDigitsForLevel(level: String) {
        val maxDigits = when (level) {
            "i3" -> 1
            "i5" -> 2
            "i7" -> 3
            else -> 1
        }
        displayRandomQuestion(maxDigits)
    }

    private fun displayRandomQuestion(maxDigits: Int) {
        val random = Random

        val number1 = random.nextInt(10.0.pow(maxDigits).toInt())
        val number2 = random.nextInt(10.0.pow(maxDigits).toInt())

        val operatorCode = random.nextInt(4)
        val operator: String = operatorArray[operatorCode]

        textView4.text = number1.toString()
        textView5.text = operator
        textView6.text = number2.toString()
    }

    private fun checkAnswer() {
        val userAnswer = editText.text.toString().toInt()
        val firstNumber = textView4.text.toString().toInt()
        val secondNumber = textView6.text.toString().toInt()
        val operator = textView5.text.toString()

        val correctAnswer = calculateAnswer(firstNumber, secondNumber, operator)

        if (userAnswer == correctAnswer) {
            points++
        } else {
            points--
        }

        textView7.text = "POINT: $points"

        setMaxDigitsForLevel(getSelectedLevel())
    }

    private fun calculateAnswer(firstNumber: Int, secondNumber: Int, operator: String): Int {
        return when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0) firstNumber / secondNumber else 0
            else -> 0
        }
    }

    private fun getSelectedLevel(): String {
        val selectedRadioButtonId = levelRadioGroup.checkedRadioButtonId
        val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
        return selectedRadioButton.text.toString()
    }
}