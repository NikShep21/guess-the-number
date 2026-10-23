package com.example.guess_number

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val minInput = findViewById<EditText>(R.id.minNumberInput)
        val maxInput = findViewById<EditText>(R.id.maxNumberInput)
        val errorText = findViewById<TextView>(R.id.errorText)

        findViewById<Button>(R.id.startButton).setOnClickListener {
            val minNumber = minInput.text.toString().trim().toIntOrNull()
            val maxNumber = maxInput.text.toString().trim().toIntOrNull()

            if (minNumber != null && maxNumber != null && minNumber < maxNumber) {
                errorText.text = ""
                startActivity(Intent(this, GuessActivity::class.java).apply {
                    putExtra(GuessActivity.EXTRA_MIN, minNumber)
                    putExtra(GuessActivity.EXTRA_MAX, maxNumber)
                })
            } else {
                errorText.setText(R.string.error_range)
            }
        }
    }
}
