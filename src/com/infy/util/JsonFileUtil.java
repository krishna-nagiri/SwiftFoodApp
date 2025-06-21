package com.infy.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class JsonFileUtil<T> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String filePath;
    private final Type type;

    public JsonFileUtil(String filePath, Type type) {
        this.filePath = filePath;
        this.type = type;
    }

    public List<T> readData() {
        try (Reader reader = new FileReader(filePath)) {
            List<T> dataList = gson.fromJson(reader, type);
            return dataList != null ? dataList : new java.util.ArrayList<>();
        } catch (IOException e) {
            return new java.util.ArrayList<>();
        }
    }

    public void writeData(List<T> dataList) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(dataList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
