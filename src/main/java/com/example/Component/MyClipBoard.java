package com.example.Component;

import com.example.graphics.MyComponent;

import java.util.ArrayList;
import java.util.List;

public class MyClipBoard {
    private static final ArrayList<MyComponent> content=new ArrayList<>();

    public static boolean isSingle=true;

    public static void setContent(List<MyComponent> newContents){
        isSingle=false;
        content.clear();//先清空
        content.addAll(newContents);
    }
    public static void setContent(MyComponent newContent){
        isSingle=true;
        content.clear();//先清空
        content.add(newContent);
    }
    public static ArrayList<MyComponent> getContent(){
        return content;
    }

}
