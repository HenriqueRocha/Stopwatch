package me.henriquerocha.android.stopwatch;

import android.os.SystemClock;

/**
 * A simple stopwatch to measure elapsed time.
 */
class Stopwatch {
    private boolean isRunning;
    private long start;
    private long stop;

    /**
     * Whether the Stopwatch is currently running.
     */
    boolean isRunning() {
        return isRunning;
    }

    /**
     * Starts the Stopwatch.
     */
    void start() {
        if (isRunning) return;

        isRunning = true;
        start += SystemClock.elapsedRealtime() - stop;
        stop = 0;
    }

    /**
     * Stops the Stopwatch.
     */
    void stop() {
        if (!isRunning) return;

        isRunning = false;
        stop = SystemClock.elapsedRealtime();
    }

    /**
     * The elapsed milliseconds.
     */
    long elapsedMillis() {
        return SystemClock.elapsedRealtime() - start;
    }

    /**
     * Resets the elapsed count to zero.
     */
    void reset() {
        start = 0;
        stop = 0;
        isRunning = false;
    }
}
