package com.example.bledatareceiver.model;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    private final String TAG = "model/FileHandler";
    private String fileName;
    private File file;

    public FileHandler(String fileName){
        this.fileName = fileName;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            file = new File(Environment.getExternalStorageDirectory(), fileName);
    }

    public void addHeader(String header){
        if (file.length() == 0)
            write(header);
    }

    public void write(String data) {
        try (FileOutputStream fos = new FileOutputStream(file)){
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
