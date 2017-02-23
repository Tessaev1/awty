package edu.uw.tessa.awty;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    public EditText messageInput;
    public EditText phoneInput;
    public EditText durationInput;
    public static String message;
    public static String phoneNumber;
    public String duration;

    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, AlarmReceiver.class);

        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        final Button start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (start.getText().toString().toLowerCase().equals("start")) {
                    getInputFields();

                    if (!checkIfValid()) {
                        return;
                    }

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);

                    start.setText("Stop");
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime(), Integer.parseInt(duration) * 60 * 1000, pendingIntent);
                } else {
                    start.setText("Start");
                    if (alarmManager!= null) {
                        alarmManager.cancel(pendingIntent);
                    }
                }
            }
        });
    }

    public void getInputFields() {
        messageInput = (EditText) findViewById(R.id.message);
        message = messageInput.getText().toString();

        phoneInput = (EditText) findViewById(R.id.number);
        phoneNumber = phoneInput.getText().toString();

        durationInput = (EditText) findViewById(R.id.duration);
        duration = durationInput.getText().toString();
    }

    public boolean checkIfValid() {
        if (message.equals("") || phoneNumber.equals("") ||
                duration.equals("")) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(duration) < 1) {
            Toast.makeText(this, "Duration cannot be under 1 minute", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
