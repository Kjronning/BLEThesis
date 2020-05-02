package com.example.bledatareceiver.model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.util.Log;

class BluetoothService {
    private final String TAG = "model/BluetoothService";
    private BluetoothAdapter BTAdapter;
    private ScanCallback cyclingCallback;
    private ScanCallback calibrationCallback;
    private DataController dataController;

    BluetoothService(DataController dataController) {
        this.dataController = dataController;
        BTAdapter = BluetoothAdapter.getDefaultAdapter();
        cyclingCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                final int rssi = result.getRssi();
                final String name = result.getDevice().getName() == null ? "Nameless" : result.getDevice().getName();
                final String address = result.getDevice().getAddress();
                if (!name.equals("RBDot"))
                    return;
                dataController.addAndCheckData(rssi, address);
            }
        };
    }

    public void startCalibrationScan(String address){
        calibrationCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                final int rssi = result.getRssi();
                final String name = result.getDevice().getName() == null ? "Nameless" : result.getDevice().getName();
                if (!name.equals("RBDot"))
                    return;
                if (!address.equals(result.getDevice().getAddress()))
                    return;
                dataController.addCalibrationData(rssi, address);
            }
        };
        startScan(calibrationCallback);
    }

    public  void startCyclingScan(){
        startScan(cyclingCallback);
    }

    public void stopCyclingScan(){
        stopScan(cyclingCallback);
    }

    public void stopCalibrationScan(){
        stopScan(calibrationCallback);
    }

    private void startScan(ScanCallback callback) {
        BTAdapter.getBluetoothLeScanner().startScan(callback);
    }

    private void stopScan(ScanCallback callback) {
        BTAdapter.getBluetoothLeScanner().stopScan(callback);
    }



}
