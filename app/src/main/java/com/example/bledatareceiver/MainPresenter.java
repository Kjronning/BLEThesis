package com.example.bledatareceiver;

import android.util.Log;

import com.example.bledatareceiver.model.DataController;


public class MainPresenter implements Presenter {
    private final String TAG = "MainPresenter";
    private ContractView contractView;
    private DataController dataController;

    MainPresenter(ContractView contractView){
        this.dataController = new DataController(this);
        this.contractView = contractView;
    }


    @Override
    public void handleDiscoveryButtonPress() {
        Log.i(TAG, "Pressed discovery button");
        dataController.startCycling();
    }

    @Override
    public void sendToast(String text) {
        contractView.showToast(text);
    }

    @Override
    public void handleScanResult(String text){
        contractView.changeInformationTextView(text);
    }

    @Override
    public void handleCalibrationButtonPress() {

    }

    @Override
    public void handleAddXButtonPress() {
        contractView.setXCoordinate(dataController.getModuloOfXSum(1));
    }

    @Override
    public void handleSubtractXButtonPress() {
        contractView.setXCoordinate(dataController.getModuloOfXSum(-1));
    }

    @Override
    public void handleAddYButtonPress() {
        contractView.setYCoordinate(dataController.getModuloOfYSum(1));

    }

    @Override
    public void handleSubtractYButtonPress() {
        contractView.setYCoordinate(dataController.getModuloOfYSum(-1));
    }

    @Override
    public void sendEmail(String send) {
        contractView.sendEmailIntent(send);
        //contractView.playReadySound();
    }

    @Override
    public void handleAddSeparationButtonPress() {
        contractView.setSeparation(dataController.addSeparation(1));
    }

    @Override
    public void handleSubtractSeparationButtonPress() {
        contractView.setSeparation(dataController.addSeparation(-1));
    }
}
