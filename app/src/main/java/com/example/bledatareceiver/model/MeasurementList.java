package com.example.bledatareceiver.model;

import android.util.Log;

import java.util.Arrays;

class MeasurementList {
    private final String TAG = "model/MeasurementList";
    private String MACAddress;
    private int calibrationRSSI;
    private int[] array;
    private int pointer;
    private boolean isFilled;
    private final int SIZE;

    public void calibrate(int rssi){
        calibrationRSSI = rssi;
    }

    MeasurementList(String MACAddress, int SIZE) {
        this.SIZE = SIZE;
        this.MACAddress = MACAddress;
        this.array = new int[SIZE];
        pointer = 0;
        isFilled = false;
    }

    String getAddress() {
        return MACAddress;
    }

    int getMean(){
        return (int)Arrays.stream(array).filter(p -> p != 0).average().orElse(1);
    }

    int getMedian(){
        return (int)Arrays.stream(array).sorted().skip((SIZE -1)/2).limit(2- SIZE %2).average().orElse(1);
    }

    void clearArray(){
        array = new int[SIZE];
        pointer = 0;
        isFilled = false;
    }

    boolean isFilled(){
        return isFilled;
    }

    void push(int value){
        //TODO: One array is never filled
        Log.d(TAG,"Pointer for MACAddress " + MACAddress + ": " + pointer);
        Log.d(TAG, "Array for MACAddress " + MACAddress + ": " + toString());
        if(isFilled)
            return;
        array[pointer++] = value;
        isFilled = array[SIZE-1] != 0;
    }

    @Override
    public String toString(){
        return Arrays.toString(array);
    }
}
