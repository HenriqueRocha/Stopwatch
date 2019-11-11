package me.henriquerocha.android.stopwatch;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class StopwatchUnitTest {
    @Test
    public void stoppedAfterCreation() {
        Stopwatch stopwatch = new Stopwatch();
        assertFalse(stopwatch.isRunning());
    }
}
