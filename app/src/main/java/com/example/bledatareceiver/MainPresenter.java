package com.example.bledatareceiver;

import android.util.Log;

import com.example.bledatareceiver.model.BluetoothHandler;

public class MainPresenter implements Presenter {
    private final String TAG = "Presenter implementation";
    private ContractView contractView;
    private BluetoothHandler bluetoothHandler;
    private boolean isScanning = false;
    private int x;
    private int y;

    MainPresenter(ContractView contractView){
        this.bluetoothHandler = new BluetoothHandler();
        this.contractView = contractView;
        bluetoothHandler.setPresenter(this);
    }

    @Override
    public void handleDiscoveryButtonPress() {
        Log.i(TAG, "Pressed discovery button");
        if(isScanning) {
            bluetoothHandler.stopScan();
            isScanning = false;
        }
        else {
            bluetoothHandler.scan();
            isScanning = true;
        }
        contractView.changeInformationTextView(bluetoothHandler.getDataListString());
    }

    @Override
    public void sendToast(String text) {
        contractView.showToast(text);
    }

    @Override
    public void handleHandlerScanResult(String text){
        contractView.changeInformationTextView(text);
    }

    @Override
    public void handleCalibrationButtonPress() {

    }

    @Override
    public void handleAddXButtonPress() {
        contractView.setXCoordinate(x = ++x%10);
    }

    @Override
    public void handleSubtractXButtonPress() {
        contractView.setXCoordinate(x = (--x%10+10)%10);
    }

    @Override
    public void handleAddYButtonPress() {
        contractView.setYCoordinate(y = ++y%10);

    }

    @Override
    public void handleSubtractYButtonPress() {
        contractView.setYCoordinate(y = (--y%10+10)%10);
    }
}
