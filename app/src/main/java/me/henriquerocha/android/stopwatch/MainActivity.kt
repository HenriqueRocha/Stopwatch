package me.henriquerocha.android.stopwatch

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private val handler = Handler()
    private val stopwatchRunnable = StopwatchRunnable()
    private val stopwatch = Stopwatch()

    private var stopwatchTextView: TextView? = null
    private var millisTextView: TextView? = null
    private var startButton: Button? = null
    private var resetButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()

        startButton!!.setOnClickListener { v -> onStartPressed() }
        resetButton!!.setOnClickListener { v -> onResetPressed() }
    }

    private fun bindViews() {
        stopwatchTextView = findViewById(R.id.stopwatchTextView)
        millisTextView = findViewById(R.id.millisTextView)
        startButton = findViewById(R.id.startStopButton)
        resetButton = findViewById(R.id.resetButton)
    }

    private fun onStartPressed() {
        if (stopwatch.isRunning) {
            stopwatch.stop()
            startButton!!.setText(R.string.start)
            handler.removeCallbacks(stopwatchRunnable)
        } else {
            stopwatch.start()
            startButton!!.setText(R.string.stop)
            resetButton!!.visibility = View.VISIBLE
            handler.postDelayed(stopwatchRunnable, DELAY_MILLIS)
        }
    }

    private fun onResetPressed() {
        stopwatch.reset()
        startButton!!.setText(R.string.start)
        resetButton!!.visibility = View.GONE
        handler.removeCallbacks(stopwatchRunnable)
        resetTextViews()
    }

    private fun resetTextViews() {
        stopwatchTextView!!.setText(R.string.zero)
        millisTextView!!.setText(R.string.zero_zero)
    }

    private inner class StopwatchRunnable : Runnable {
        override fun run() {
            val elapsedMillis = stopwatch.elapsedMillis()
            val minutes = elapsedMillis / 1000 / 60
            val seconds = elapsedMillis / 1000 % 60
            val millis = elapsedMillis % 1000 / 10
            val format = selectFormat(minutes, seconds)
            stopwatchTextView!!.text = format
            millisTextView!!.setText(String.format(Locale.getDefault(), "%02d", millis))
            handler.postDelayed(this, DELAY_MILLIS)
        }

        private fun selectFormat(minutes: Long, seconds: Long): String {
            val locale = Locale.getDefault()
            return if (minutes == 0L) {
                String.format(locale, "%d", seconds)
            } else String.format(locale, "%d:%02d", minutes, seconds)
        }
    }

    companion object {
        private val DELAY_MILLIS: Long = 30
    }
}
