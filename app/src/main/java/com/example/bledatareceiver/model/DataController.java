package com.example.bledatareceiver.model;

import android.util.Log;

import com.example.bledatareceiver.MainPresenter;

public class DataController {
    private final String TAG = "model/DataController";
    private int x;
    private int y;
    private int cycles;


    private MeasurementListRepository measurementListRepository;
    private BluetoothService bluetoothService;
    private FileHandler fileHandler;

    private MainPresenter presenter;


    public DataController(MainPresenter presenter){
        this.presenter = presenter;
        measurementListRepository = new MeasurementListRepository();
        fileHandler = new FileHandler(System.currentTimeMillis() + "");
        bluetoothService = new BluetoothService(this);
    }


    public void clearDataArrays() {
        measurementListRepository.clearDataArrays();
    }


    public void addAndCheckData(int rssi, String address) {
        measurementListRepository.setOrAddItem(rssi, address);
        if (measurementListRepository.isReady()) {
            presenter.sendToast("Arrays filled!");
            writeHeader("x y " + measurementListRepository.getAddresses());
            String data = x + " " + y + " " + measurementListRepository.getDataAsString();
            saveToFile(data);
            Log.d(TAG, "Saving data: " +  data);
            clearDataArrays();
            cycles++;
        }
        presenter.handleScanResult(measurementListRepository.getDataListString());
        if(cycles == 9)
            stopCycling();
    }

    private void stopCycling() {
        clearDataArrays();
        presenter.sendToast("Data saved!");
        presenter.sendEmail(fileHandler.send());
        bluetoothService.stopCyclingScan();
    }

    public void startCycling(){
        cycles = 0;
        bluetoothService.startCyclingScan();
    }


    private void writeHeader(String header) {
        fileHandler.addHeader(header);
    }

    private void saveToFile(String dataAsString) {
        String line = String.format("%d %d %s \b", x, y, dataAsString);
        fileHandler.append(line);
    }

    public int getModuloOfXSum(int i) {
        return x = (x+i)%10;
    }

    public int getModuloOfYSum(int i) {
        return y = (y+i)%10;
    }

    public void addCalibrationData(int rssi, String address) {
        measurementListRepository.setOrAddItem(rssi, address);
        MeasurementList measurementList = measurementListRepository.getArray(address);
        if(measurementList==null)
            return;
        if (measurementList.isFilled()) {
            measurementList.calibrate(measurementList.getMean());
            bluetoothService.stopCalibrationScan();
        }
    }
}
