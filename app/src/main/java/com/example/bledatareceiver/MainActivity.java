package com.example.bledatareceiver;

import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bledatareceiver.model.BluetoothHandler;

public class MainActivity extends AppCompatActivity implements ContractView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothHandler bluetoothHandler = new BluetoothHandler();
        registerReceiver(bluetoothHandler.getReceiver(), new IntentFilter(BluetoothDevice.ACTION_FOUND));
        MainPresenter presenter = new MainPresenter(bluetoothHandler);
    }


    @Override
    public void addXAxis() {

    }

    @Override
    public void subtractXAxis() {

    }

    @Override
    public void addYAxis() {

    }

    @Override
    public void subtractYAxis() {

    }
}
