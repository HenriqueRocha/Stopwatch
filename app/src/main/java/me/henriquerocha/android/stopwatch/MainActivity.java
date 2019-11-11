package me.henriquerocha.android.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Locale;

public class MainActivity extends Activity {
    private final Handler handler = new Handler();
    private final Runnable stopwatchRunnable = new StopwatchRunnable();
    private final Stopwatch stopwatch = new Stopwatch();

    private TextView stopwatchTextView;
    private TextView millisTextView;
    private Button startButton;
    private Button resetButton;

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
                    resetButton.setVisibility(View.VISIBLE);
                    handler.post(stopwatchRunnable);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.reset();
                startButton.setText(R.string.start);
                resetButton.setVisibility(View.GONE);
                handler.removeCallbacks(stopwatchRunnable);
                resetTextViews();
            }
        });

    }

    private void bindViews() {
        stopwatchTextView = findViewById(R.id.stopwatchTextView);
        millisTextView = findViewById(R.id.millisTextView);
        startButton = findViewById(R.id.startStopButton);
        resetButton = findViewById(R.id.resetButton);
    }

    private void resetTextViews() {
        stopwatchTextView.setText(R.string.zero);
        millisTextView.setText(R.string.zero_zero);
    }

    private class StopwatchRunnable implements Runnable {
        @Override
        public void run() {
            long elapsedMillis = stopwatch.elapsedMillis();
            long minutes = elapsedMillis / 1000 / 60;
            long seconds = elapsedMillis / 1000 % 60;
            long millis = elapsedMillis % 1000 / 10;
            String format = selectFormat(minutes, seconds);
            stopwatchTextView.setText(format);
            millisTextView.setText(String.format(Locale.getDefault(), "%02d", millis));
            handler.post(this);
        }

        private String selectFormat(long minutes, long seconds) {
            Locale locale = Locale.getDefault();
            if (minutes == 0) {
                return String.format(locale, "%d", seconds);
            }
            return String.format(locale, "%d:%02d", minutes, seconds);
        }
    }
}
