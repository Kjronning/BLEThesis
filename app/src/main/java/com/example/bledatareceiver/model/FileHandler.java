package com.example.bledatareceiver.model;

public class FileHandler {
    private final String TAG = "model/FileHandler";
    private String fileName;
    private StringBuilder data;

    public FileHandler(String fileName){
        this.fileName = fileName;
        data = new StringBuilder();

    }

    public void addHeader(String header){
        if (data.length() == 0)
            append(header);
    }

    public void append(String data) {
        this.data.append(data);
    }

    public String send(){
        return data.toString();
    }
}
