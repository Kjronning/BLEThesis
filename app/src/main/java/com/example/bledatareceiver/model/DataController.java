package com.example.bledatareceiver.model;

import android.util.Log;

import com.example.bledatareceiver.MainPresenter;

public class DataController {
    private final String TAG = "model/DataController";
    private int x;
    private int y;
    private int separation = 1;
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
            writeHeader("separation x y " + measurementListRepository.getAddresses());
            String data = measurementListRepository.getDataAsString();
            saveToFile(data);
            Log.d(TAG, "Saving data: " +  data + " for cycle " + ++cycles);
            clearDataArrays();
        }
        presenter.handleScanResult(measurementListRepository.getDataListString());
        if(cycles == 10)
            stopCycling();
    }

    private void stopCycling() {
        presenter.sendToast("Data saved!");
        presenter.sendEmail(fileHandler.send());
        bluetoothService.stopCyclingScan();
    }

    private void clearData() {
        clearDataArrays();
        fileHandler.clear();
    }

    public void startCycling(){
        cycles = 0;
        clearData();
        bluetoothService.startCyclingScan();
    }


    private void writeHeader(String header) {
        fileHandler.addHeader(header);
    }

    private void saveToFile(String dataAsString) {
        String line = String.format("%d %d %d %s \b", separation, x, y, dataAsString);
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

    public int addSeparation(int value) {
        return separation += value;
    }
}
