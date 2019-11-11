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
    private final Stopwatch stopwatch = new Stopwatch();

    final long initialTime = SystemClock.elapsedRealtime();

    private TextView stopwatchTextView;
    private Button startButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stopwatch.isRunning()) {
                    stopwatch.stop();
                    startButton.setText(R.string.start);
                    handler.removeCallbacks(stopwatchRunnable);
                } else {
                    stopwatch.start();
                    startButton.setText(R.string.stop);
                    handler.post(stopwatchRunnable);
                }
            }
        });
    }

    private void bindViews() {
        stopwatchTextView = findViewById(R.id.stopwatchTextView);
        startButton = findViewById(R.id.startStopButton);
    }

    private class StopwatchRunnable implements Runnable {
        @Override
        public void run() {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            stopwatchTextView.setText(String.valueOf(elapsedRealtime - initialTime));
            handler.post(this);
        }
    }
}
