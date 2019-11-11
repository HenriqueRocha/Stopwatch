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
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final long initialTime = SystemClock.elapsedRealtime();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView stopwatchTextView = findViewById(R.id.stopwatchTextView);
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        stopwatchTextView.setText(String.valueOf(elapsedRealtime - initialTime));
                        handler.post(this);
                    }
                });

            }
        });
    }
}
