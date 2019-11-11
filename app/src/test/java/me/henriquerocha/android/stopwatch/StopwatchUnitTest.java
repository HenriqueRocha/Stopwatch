package me.henriquerocha.android.stopwatch;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StopwatchUnitTest {
    @Test
    public void stoppedAfterCreation() {
        Stopwatch stopwatch = new Stopwatch();
        assertFalse(stopwatch.isRunning());
    }

    @Test
    public void isRunningAfterStart() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start();

        assertTrue(stopwatch.isRunning());
    }

    @Test
    public void notIsRunningAfterStop() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start();
        stopwatch.stop();

        assertFalse(stopwatch.isRunning());
    }
}
