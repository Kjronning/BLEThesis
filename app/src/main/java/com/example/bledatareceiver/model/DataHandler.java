package com.example.bledatareceiver.model;

import android.util.Log;

import java.util.ArrayList;

public class DataHandler {
    private static final String TAG = "Data Handler";
    private ArrayList<RSSIData> data;

    public DataHandler(){
        data = new ArrayList<>();
    }

    void setOrAddItem(final int rssi, final String MACAddress) {
        if(data.stream().noneMatch(o->o.getMACAddress().equals(MACAddress))) {
            RSSIData item = new RSSIData(MACAddress);
            data.add(item);
        }else {
            data.stream().filter(o -> o.getMACAddress().equals(MACAddress)).findFirst().orElse(null).fillRSSI(rssi);
        }
    }

    void saveData() {
        for(RSSIData item : data){
            Log.d(TAG, "Mean for device " + item.getMACAddress() + ": " + item.getRSSIMean());
            Log.d(TAG, "Median for device " + item.getMACAddress() + ": " + item.getRSSIMedian());
        }
    }

    String getDataListString() {
        StringBuilder list = new StringBuilder("Device List\n");
        for (RSSIData item : data) {
            list.append("name: ").append(item.getMACAddress()).append(" RSSI: ").append(item.getLastAdded()).append("\n");
        }
        return list.toString();
    }

    void clearData() {
        for(RSSIData item : data){
            item.clearRSSI();
        }
    }

    boolean isReady() {
        return data.stream().allMatch(RSSIData::isFilled);
    }
}
