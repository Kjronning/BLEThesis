package com.example.bledatareceiver.model;

import android.util.Log;

import java.util.Arrays;

class RSSIData {
    private final String TAG = "RSSIData";
    private String MACAddress;
    private int[] rssi;
    private int pointer;
    private boolean isFilled;


    RSSIData(String MACAddress) {
        this.MACAddress = MACAddress;
        this.rssi = new int[20];
        pointer = 0;
        isFilled = false;
    }

    String getMACAddress() {
        return MACAddress;
    }

    int[] getRSSI() {
        return rssi;
    }

    int getRSSIMean(){
        return (int)Arrays.stream(rssi).average().orElse(1);
    }

    void clearRSSI(){
        rssi = new int[20];
        pointer = 0;
        isFilled = false;
    }

    int getRSSIMedian(){
        int size = rssi.length;
        return (int)Arrays.stream(rssi).sorted().skip((size-1)/2).limit(2-size%2).average().orElse(1);
    }

    int getLastAdded(){
        return rssi[pointer <= 0 ? 0 : pointer-1];
    }

    boolean isFilled(){
        return isFilled;
    }

    void fillRSSI(int value){
        //TODO: One array is never filled
        Log.d(TAG,"Pointer for MACAddress " + MACAddress + ": " + pointer);
        Log.d(TAG, "Array for MACAddress " + MACAddress + ": " + toString());
        if(isFilled)
            return;
        rssi[pointer] = value;
        pointer++;
        isFilled = rssi[rssi.length-1] != 0;
    }

    @Override
    public String toString(){
        return Arrays.toString(rssi);
    }
}
