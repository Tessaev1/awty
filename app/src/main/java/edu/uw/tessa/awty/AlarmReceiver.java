package edu.uw.tessa.awty;

import android.app.Activity;
import android.content.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Tessa on 2/16/17.
 */

public final class AlarmReceiver extends BroadcastReceiver {
    public final static String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String toastMessage = "Texting {" + MainActivity.phoneNumber + "} \n" + MainActivity.message;
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();

        /*
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
        Toast.makeText(context, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();
         */
    }
}
