package me.henriquerocha.android.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private final Handler handler = new Handler();
    private final Runnable stopwatchRunnable = new StopwatchRunnable();

    final long initialTime = SystemClock.elapsedRealtime();

    private boolean isRunning;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startButton = findViewById(R.id.startStopButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                   startButton.setText(R.string.start);
                   handler.removeCallbacks(stopwatchRunnable);
                } else {
                    startButton.setText(R.string.stop);
                    handler.post(stopwatchRunnable);
                }
                isRunning = !isRunning;
            }
        });
    }

    private class StopwatchRunnable implements Runnable {
        @Override
        public void run() {
            TextView stopwatchTextView = findViewById(R.id.stopwatchTextView);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            stopwatchTextView.setText(String.valueOf(elapsedRealtime - initialTime));
            handler.post(this);
        }
    }
}
