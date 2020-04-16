package com.example.bledatareceiver;

import android.util.Log;

import com.example.bledatareceiver.model.BluetoothHandler;

public class MainPresenter implements Presenter {
    private final String TAG = "Presenter implementation";
    private ContractView contractView;
    private BluetoothHandler bluetoothHandler;

    public MainPresenter(BluetoothHandler bluetoothHandler, ContractView contractView){
        this.bluetoothHandler = bluetoothHandler;
        this.contractView = contractView;
    }

    public void startDiscovery(){
        bluetoothHandler.startDiscovery();
    }

    @Override
    public void handleDiscoveryButtonPress() {
        Log.i(TAG, "Pressed discovery button");
        bluetoothHandler.startDiscovery();
        contractView.changeInformationTextView(bluetoothHandler.getDataListString());
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
