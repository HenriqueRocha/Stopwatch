package me.henriquerocha.android.stopwatch;

/**
 * A simple stopwatch to measure elapsed time.
 */
class Stopwatch {
    private boolean isRunning;

    boolean isRunning() {
        return isRunning;
    }

    /**
     * Starts the Stopwatch.
     */
    void start() {
        isRunning = true;
    }

    /**
     * Stops the Stopwatch.
     */
    void stop() {
        isRunning = false;
    }
}
