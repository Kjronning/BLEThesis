package com.example.bledatareceiver.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.StringJoiner;

public class MeasurementListRepository {
    private static final String TAG = "model/MeasurementListRepository";
    private ArrayList<MeasurementList> dataArrays;

    public MeasurementListRepository(){
        dataArrays = new ArrayList<>();
    }

    void setOrAddItem(final int rssi, final String MACAddress) {
        dataArrays.stream().filter(measurementList -> measurementList.getAddress().equals(MACAddress)).findFirst().orElse(createItem(MACAddress)).push(rssi);
    }

    private MeasurementList createItem(String MACAddress) {
        MeasurementList item = new MeasurementList(MACAddress, 20);
        dataArrays.add(item);
        return item;
    }

    String getDataAsString() {
        StringJoiner joiner = new StringJoiner(" ");
        for(MeasurementList item : dataArrays){
            joiner.add(item.getMean()+"");
        }
        return joiner.toString();
    }

    String getAddresses(){
        StringJoiner joiner = new StringJoiner(" ");
        for(MeasurementList item : dataArrays){
            joiner.add(item.getAddress()+"");
        }
        return joiner.toString();
    }

    ArrayList<MeasurementList> getArrays(){
        return dataArrays;
    }

    String getDataListString() {
        StringBuilder list = new StringBuilder("Device List\n");
        for (MeasurementList item : dataArrays) {
            list.append("name: ").append(item.getAddress()).append(" RSSI: ").append(item.getMean()).append("\n");
        }
        return list.toString();
    }

    void clearDataArrays() {
        for(MeasurementList item : dataArrays){
            item.clearArray();
        }
    }

    boolean isReady() {
        return dataArrays.stream().allMatch(MeasurementList::isFilled);
    }

    public MeasurementList getArray(String address) {
        return dataArrays.stream().filter(o -> o.getAddress().equals(address)).findFirst().orElse(null);
    }
}
