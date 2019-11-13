package me.henriquerocha.android.stopwatch

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val handler = Handler()
    private val stopwatchRunnable = StopwatchRunnable()
    private var stopwatch = Stopwatch()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            restoreState(savedInstanceState)
        }

        startButton.setOnClickListener { onStartPressed() }
        resetButton.setOnClickListener { onResetPressed() }
    }

    override fun onResume() {
        super.onResume()
        if (stopwatch.isRunning) {
            handler.post(stopwatchRunnable)
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(stopwatchRunnable)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_STOPWATCH, stopwatch)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        stopwatch = savedInstanceState.get(KEY_STOPWATCH) as Stopwatch
        if (stopwatch.isRunning) {
            startButton.setText(R.string.stop)
        } else {
            val elapsedMillis = stopwatch.elapsedMillis()
            if (elapsedMillis != 0L) {
                formatStopwatchMillis(elapsedMillis)
            }
        }
    }

    private fun onStartPressed() {
        if (stopwatch.isRunning) {
            stopwatch.stop()
            startButton.setText(R.string.start)
            handler.removeCallbacks(stopwatchRunnable)
        } else {
            stopwatch.start()
            startButton.setText(R.string.stop)
            resetButton.visibility = View.VISIBLE
            handler.postDelayed(stopwatchRunnable, DELAY_MILLIS)
        }
    }

    private fun onResetPressed() {
        stopwatch.reset()
        startButton.setText(R.string.start)
        resetButton.visibility = View.GONE
        handler.removeCallbacks(stopwatchRunnable)
        resetTextViews()
    }

    private fun resetTextViews() {
        stopwatchTextView.setText(R.string.zero)
        millisTextView.setText(R.string.zero_zero)
    }

    private fun formatStopwatchMillis(elapsedMillis: Long) {
        val minutes = elapsedMillis / 1000 / 60
        val seconds = elapsedMillis / 1000 % 60
        val millis = elapsedMillis % 1000 / 10
        val format = selectFormat(minutes, seconds)
        stopwatchTextView.text = format
        millisTextView.text = String.format(Locale.getDefault(), "%02d", millis)
    }

    private fun selectFormat(minutes: Long, seconds: Long): String {
        val locale = Locale.getDefault()
        return if (minutes == 0L) {
            String.format(locale, "%d", seconds)
        } else String.format(locale, "%d:%02d", minutes, seconds)
    }

    private inner class StopwatchRunnable : Runnable {
        override fun run() {
            val elapsedMillis = stopwatch.elapsedMillis()
            formatStopwatchMillis(elapsedMillis)
            handler.postDelayed(this, DELAY_MILLIS)
        }
    }

    companion object {
        private const val DELAY_MILLIS: Long = 30
        private const val KEY_STOPWATCH = "stopwatch"
    }
}
