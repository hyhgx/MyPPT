package com.example.graphics;

import java.awt.*;

public class MyImage extends MyComponent2D {

    private final Image image;
    public MyImage(int x,int y ,Image image){
        super(x,y);
        this.image=image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       // g.drawImage()
        //g.drawImage(5,5,maxX-minX,maxY-minY)
    }
}
