package com.example.exinputdevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.bluetooth.BluetoothDevice;
import android.widget.TextView;

public class BtBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)) {
            BluetoothDevice device = intent.getParcelableExtra(
                    BluetoothDevice.EXTRA_DEVICE);
            String name = device.getName();
            /*
            String s = deviceTextView.getText().toString();
            s += (name == null ? "n/a" : name) + " [" + device.getAddress() + "]\r\n";
            deviceTextView.setText(s);
            */

        }
    }
}
