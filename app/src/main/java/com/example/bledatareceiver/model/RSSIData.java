package com.example.bledatareceiver.model;

public class RSSIData {
    private String label;
    private int value;
    private long timeStamp;


    public RSSIData(String label, int value, long timeStamp) {
        this.label = label;
        this.value = value;
        this.timeStamp = timeStamp;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }
}
