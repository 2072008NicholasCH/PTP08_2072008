package com.example.ptp08_2072008.dao;

import com.example.ptp08_2072008.model.ItemEntity;

import java.util.List;

public interface interfaceDao <T> {
    List<T> getData();
    void setData(T data);
    void delData(T data);
    void upData(T data);
}
