package me.henriquerocha.android.stopwatch

import android.os.SystemClock
import java.io.Serializable

/**
 * A simple stopwatch to measure elapsed time.
 */
internal class Stopwatch : Serializable {
    /**
     * Whether the Stopwatch is currently running.
     */
    var isRunning: Boolean = false
        private set
    private var start: Long = 0
    private var stop: Long = 0

    /**
     * Starts the Stopwatch.
     */
    fun start() {
        if (isRunning) return

        isRunning = true
        start += SystemClock.elapsedRealtime() - stop
        stop = 0
    }

    /**
     * Stops the Stopwatch.
     */
    fun stop() {
        if (!isRunning) return

        isRunning = false
        stop = SystemClock.elapsedRealtime()
    }

    /**
     * The elapsed milliseconds.
     */
    fun elapsedMillis(): Long {
        return if (isRunning) {
            SystemClock.elapsedRealtime() - start
        } else {
            stop - start
        }
    }

    /**
     * Resets the elapsed count to zero.
     */
    fun reset() {
        start = 0
        stop = 0
        isRunning = false
    }
}
