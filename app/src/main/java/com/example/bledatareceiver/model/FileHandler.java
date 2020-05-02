package com.example.bledatareceiver.model;

import android.util.Log;

public class FileHandler {
    private final String TAG = "model/FileHandler";
    private String fileName;
    private StringBuilder data;

    public FileHandler(String fileName){
        this.fileName = fileName;
        data = new StringBuilder();

    }

    public void clear(){
        data = new StringBuilder();
    }

    public void addHeader(String header){
        if (data.length() == 0)
            append(header);
    }

    public void append(String data) {
        this.data.append(data).append(System.lineSeparator());
        Log.d(TAG, this.data.toString());
    }

    public String send(){
        return data.toString();
    }
}
