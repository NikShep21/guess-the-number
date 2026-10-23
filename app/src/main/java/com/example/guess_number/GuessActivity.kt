package com.example.guess_number

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class GuessActivity : ComponentActivity() {
    private var minNumber = 0
    private var maxNumber = 0
    private var attempts = 1
    private lateinit var rangeText: TextView
    private lateinit var guessText: TextView
    private lateinit var attemptsText: TextView
    private lateinit var hintText: TextView
    private lateinit var greaterButton: Button
    private lateinit var lessButton: Button
    private lateinit var correctButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess)

        minNumber = intent.getIntExtra(EXTRA_MIN, 1)
        maxNumber = intent.getIntExtra(EXTRA_MAX, 100)

        rangeText = findViewById(R.id.currentRangeText)
        guessText = findViewById(R.id.guessNumberText)
        attemptsText = findViewById(R.id.attemptsText)
        hintText = findViewById(R.id.hintText)
        greaterButton = findViewById(R.id.greaterButton)
        lessButton = findViewById(R.id.lessButton)
        correctButton = findViewById(R.id.correctButton)

        greaterButton.setOnClickListener {
            minNumber = currentGuess() + 1
            attempts++
            showNextGuess()
        }
        lessButton.setOnClickListener {
            maxNumber = currentGuess() - 1
            attempts++
            showNextGuess()
        }
        correctButton.setOnClickListener { showSuccessDialog() }
        findViewById<Button>(R.id.changeRangeButton).setOnClickListener { finish() }

        showNextGuess()
    }

    private fun currentGuess() = minNumber + (maxNumber - minNumber) / 2

    private fun showNextGuess() {
        rangeText.text = getString(R.string.current_range, minNumber, maxNumber)
        guessText.text = currentGuess().toString()
        attemptsText.text = getString(R.string.attempts_count, attempts)
        hintText.setText(
            if (minNumber == maxNumber) R.string.only_number_left
            else R.string.answer_hint
        )
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.success_title)
            .setMessage(getString(R.string.success_message, currentGuess(), attempts))
            .setPositiveButton(R.string.play_again) { _, _ -> finish() }
            .setNegativeButton(R.string.stay_here, null)
            .show()
    }

    companion object {
        const val EXTRA_MIN = "extra_min"
        const val EXTRA_MAX = "extra_max"
    }
}
