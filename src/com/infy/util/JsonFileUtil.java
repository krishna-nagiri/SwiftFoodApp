package com.infy.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.infy.data.LocalDateTimeAdapter;
import com.infy.data.LocalTimeAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class JsonFileUtil<T> {
    private final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
        .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
        .setPrettyPrinting()
        .create();

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
