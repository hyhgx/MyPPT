package com.example.graphics;

import java.awt.*;

public class MyImage extends MyComponent2D {

    private final Image image;

    public MyImage(int minX,int minY,int maxX,int maxY,Image image){
        super(0,0,null);
        this.minX=minX;
        this.minY=minY;
        this.maxX=maxX;
        this.maxY=maxY;
        this.image=image;
        this.setBounds(minX-5,minY-5,maxX-minX+10,maxY-minY+10);
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,5,5,maxX-minX,maxY-minY,new Color(255,255,255),null);
    }
}
