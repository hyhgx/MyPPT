package com.example.graphics;

import javax.swing.*;
import java.awt.*;

public class MyOuter extends JComponent {
    private Color color=new Color(0,0,0);
    public MyOuter(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(50,40));
    }
    public void changeColor(int x,int y,int z){
        color=new Color(x,y,z);
        this.repaint();
    }
    public  void changeColor1(Color x)
    {
        color=x;
        this.repaint();
    }
    public Color getColor(){
        return color;
    }
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(color);
        g.fillRect(10,0,30,30);
        g.setColor(new Color(0,0,0));
        g.drawRoundRect(0,0,50,43,5,5);
        g.drawString("当前颜色",0,40);
    }
}
