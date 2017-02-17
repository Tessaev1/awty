package edu.uw.tessa.awty;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    Button start;
    Toast messageSent;
    String message;
    String phoneNumber;

    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Context context = getApplicationContext();
//        int duration = Toast.LENGTH_SHORT;

        final EditText messageInput = (EditText) findViewById(R.id.message);
        final EditText phoneInput = (EditText) findViewById(R.id.number);

        start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = messageInput.getText().toString();
                Log.i(TAG, "message: " + message);
                phoneNumber = phoneInput.getText().toString();
                messageSent = Toast.makeText(getApplicationContext(), phoneNumber + ": " + message, Toast.LENGTH_SHORT);
                messageSent.show();
            }
        });
    }
}
