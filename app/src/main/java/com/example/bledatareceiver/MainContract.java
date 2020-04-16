package com.example.bledatareceiver;

public interface MainContract {
}

interface ContractView {
    void addXAxis();

    void subtractXAxis();

    void addYAxis();

    void subtractYAxis();
}
interface Presenter {
    void handleDiscoveryButtonPress();

    void handleCalibrationButtonPress();

    void handleAddXButtonPress();

    void handleSubtractXButtonPress();

    void handleAddYButtonPress();

    void handleSubtractYButtonPress();
}
