package com.example.hackturin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcastReceiverCustom extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        double latitude = Double.valueOf(intent.getStringExtra("latutide"));
        double longitude = Double.valueOf(intent.getStringExtra("longitude"));

    }
}
