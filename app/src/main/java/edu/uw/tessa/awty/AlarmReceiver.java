package edu.uw.tessa.awty;

import android.content.*;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Tessa on 2/16/17.
 */

public final class AlarmReceiver extends BroadcastReceiver {
    public final static String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(MainActivity.phoneNumber, null, MainActivity.message, null, null);
            Log.i(TAG, "Alarm Triggered and SMS Sent");
        } catch (Exception ex) {
            Toast.makeText(context, "SMS failed, please try again later.", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "SMS failed: " + ex);
        }
    }
}
