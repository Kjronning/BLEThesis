package com.example.bledatareceiver;

import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bledatareceiver.model.BluetoothHandler;

public class MainActivity extends AppCompatActivity implements ContractView {

    TextView xAxis, yAxis, informationTextView;
    Button addXButton, addYButton, subtractXButton, subractYButton, discoveryButton;
    MainPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothHandler bluetoothHandler = new BluetoothHandler();
        registerReceiver(bluetoothHandler.getReceiver(), new IntentFilter(BluetoothDevice.ACTION_FOUND));
        presenter = new MainPresenter(bluetoothHandler, this);
        presenter.startDiscovery();
        informationTextView = findViewById(R.id.informationTextView);
        discoveryButton = findViewById(R.id.discoveryButton);
        discoveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.handleDiscoveryButtonPress();
            }
        });
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

    @Override
    public void changeInformationTextView(String text) {
        informationTextView.setText(text);
    }
}
