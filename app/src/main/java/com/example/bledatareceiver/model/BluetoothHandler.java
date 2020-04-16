package com.example.bledatareceiver.model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothHandler {

    private BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
    private String name;
    private int rssi;


    private final BroadcastReceiver receiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
            }
        }
    };

    public void startDiscovery(){
        BTAdapter.startDiscovery();
    }

    public BroadcastReceiver getReceiver(){
        return receiver;
    }

    public String getName(){
        return name;
    }

    public int getRSSI(){
        return rssi;
    }
}
