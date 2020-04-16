package com.example.bledatareceiver.model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

public class BluetoothHandler {
    private final String TAG = "BluetoothHandler Class";
    private BluetoothAdapter BTAdapter;
    private ArrayList<RSSIData> data;
    private final BroadcastReceiver receiver;


    public BluetoothHandler() {
        BTAdapter = BluetoothAdapter.getDefaultAdapter();
        data = new ArrayList<>();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "onReceive method called");
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                    String label = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                    setOrAddDatum(rssi, label, System.currentTimeMillis());

                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    String deviceName = device.getName();
                    Log.i("Bluetooth", "got device " + deviceName);
                }
            }
        };
    }


    private void setOrAddDatum(int rssi, String label, long currentTime) {
        for (RSSIData datum : data) {
            Log.i(TAG, datum.toString());
            if (datum.getLabel().equals(label)) {
                datum.setTimeStamp(currentTime);
                datum.setValue(rssi);
            } else {
                data.add(new RSSIData(label, rssi, currentTime));
            }
        }
    }

    public String getDataListString() {
        StringBuilder list = new StringBuilder();
        for (RSSIData datum : data) {
            list.append("name: ").append(datum.getLabel()).append(" RSSI: ").append(datum.getValue()).append("\n");
        }
        return list.toString();
    }

    public void startDiscovery() {
        BTAdapter.startDiscovery();
    }

    public BroadcastReceiver getReceiver() {
        return receiver;
    }
}
