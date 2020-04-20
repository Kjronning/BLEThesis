package com.example.bledatareceiver;

public interface MainContract {
}

interface ContractView {
    void showToast(String text);

    void setXCoordinate(int x);

    void setYCoordinate(int y);

    void changeInformationTextView(String text);
}
interface Presenter {
    void handleDiscoveryButtonPress();

    void sendToast(String text);

    void handleHandlerScanResult(String text);

    void handleCalibrationButtonPress();

    void handleAddXButtonPress();

    void handleSubtractXButtonPress();

    void handleAddYButtonPress();

    void handleSubtractYButtonPress();
}
