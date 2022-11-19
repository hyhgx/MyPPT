package com.example.Component;

import java.util.LinkedList;
import java.util.List;

public class JsonList {
    public List<JSONdata> jsonDataList=new LinkedList<>();
    public JsonList(){}

    @Override
    public String toString() {
        return "JsonList{" +
                "jsonDataList=" + jsonDataList +
                '}';
    }
}
