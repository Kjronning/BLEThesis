package com.example.bledatareceiver;

public interface MainContract {
}

interface ContractView {
    void showToast(String text);

    void setXCoordinate(int x);

    void setYCoordinate(int y);

    void changeInformationTextView(String text);

    void sendEmailIntent(String send);

    void setSeparation(int value);

    void playReadySound();
}
interface Presenter {
    void handleDiscoveryButtonPress();

    void sendToast(String text);

    void handleScanResult(String text);

    void handleCalibrationButtonPress();

    void handleAddXButtonPress();

    void handleSubtractXButtonPress();

    void handleAddYButtonPress();

    void handleSubtractYButtonPress();

    void sendEmail(String send);

    void handleAddSeparationButtonPress();

    void handleSubtractSeparationButtonPress();
}
