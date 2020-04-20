package com.example.bledatareceiver.model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.util.Log;

import com.example.bledatareceiver.MainPresenter;
import java.util.List;

public class BluetoothHandler {
    private final String TAG = "BluetoothHandler Class";
    private BluetoothAdapter BTAdapter;
    private DataHandler dataHandler;
    private ScanCallback callback;
    private MainPresenter presenter;


    public BluetoothHandler() {
        BTAdapter = BluetoothAdapter.getDefaultAdapter();
        dataHandler = new DataHandler();
        callback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                final int rssi = result.getRssi();
                final String name = result.getDevice().getName() == null ? "Nameless" : result.getDevice().getName();
                final String address = result.getDevice().getAddress();
                if (!name.equals("RBDot"))
                    return;
                addAndCheckData(rssi, address);
                presenter.handleHandlerScanResult(dataHandler.getDataListString());
            }

            @Override
            public void onScanFailed(int errorCode) {
                Log.d(TAG, "Scan failed with error code: " + errorCode);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                Log.d(TAG, results.toString());
            }
        };
    }

    public String getDataListString(){
        return dataHandler.getDataListString();
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    public void scan() {
        dataHandler.clearData();
        BTAdapter.getBluetoothLeScanner().startScan(callback);
    }

    public void stopScan() {
        BTAdapter.getBluetoothLeScanner().stopScan(callback);
    }


    private void addAndCheckData(final int rssi, final String MACAddress) {
        dataHandler.setOrAddItem(rssi, MACAddress);
        if (dataHandler.isReady()) {
            presenter.sendToast("Arrays filled!");
            dataHandler.saveData();
            stopScan();
        }
    }
}
