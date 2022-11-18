package com.example.graphics;

import com.example.Component.CanvasPanel;
import com.example.Component.CanvasPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyRoundRect extends MyComponent2D {
    public MyRoundRect(int x,int y){
        super(x,y,null);
        this.setName("圆角矩形");
    }
    public MyRoundRect(int x,int y,Color lineColor){
        super(x,y,lineColor);
        this.setName("圆角矩形");
    }
    public MyRoundRect(int minX,int minY,int maxX,int maxY,String name, Color lineColor,Color fillcolor){
        super();
        this.fillColor=fillcolor;
        this.lineColor=lineColor;
        this.minX=minX;
        this.minY=minY;
        this.maxX=maxX;
        this.maxY=maxY;
        this.setName(name);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BasicStroke basicStroke = new BasicStroke(1.0f);
        Graphics2D g1=(Graphics2D) g;
        g1.setStroke(basicStroke);
        g1.setColor(lineColor);
        g1.drawRoundRect(5,5,maxX-minX,maxY-minY,10,10);
        g1.setColor(fillColor);
        g1.fillRoundRect(6,6,maxX-minX-2,maxY-minY-2,10,10);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setBounds(0,0,1000,800);
        final CanvasPanel panel = new CanvasPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,1000,700);
        panel.setBackground(new Color(123,123,123));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                MyRoundRect myRoundRect= new MyRoundRect(e.getX(),e.getY());
                panel.add(myRoundRect);
                panel.focusChanged();
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                MyRoundRect myRoundRect = (MyRoundRect) panel.getComponent(panel.getComponentCount() - 1);
                myRoundRect.setX2Y2(e.getX(),e.getY());
            }
        });
        jFrame.add(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
