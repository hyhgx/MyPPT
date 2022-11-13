package com.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyComponent extends JComponent {
    protected int x1;
    protected int x2;
    protected int y1;
    protected int y2;

    protected int initX;
    protected int initY;
    protected ArrayList<MyPoint> myPoints=new ArrayList<>();

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    protected void setX2Y2(int x,int y){}
    protected void lostFocus(){
        if(myPoints.size()!=0){
            for(MyPoint p:myPoints){
                p.setVisible(false);
            }
        }
    }

    public void getFocus(){

    }
    protected void updatePoints(){}
}
