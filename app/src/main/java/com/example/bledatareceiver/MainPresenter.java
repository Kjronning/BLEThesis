package com.example.bledatareceiver;

import com.example.bledatareceiver.model.BluetoothHandler;

public class MainPresenter implements Presenter {

    private BluetoothHandler bluetoothHandler;
    public MainPresenter(BluetoothHandler bluetoothHandler){
        this.bluetoothHandler = bluetoothHandler;
    }

    @Override
    public void handleDiscoveryButtonPress() {

    }

    @Override
    public void handleCalibrationButtonPress() {

    }

    @Override
    public void handleAddXButtonPress() {

    }

    @Override
    public void handleSubtractXButtonPress() {

    }

    @Override
    public void handleAddYButtonPress() {

    }

    @Override
    public void handleSubtractYButtonPress() {

    }
}
