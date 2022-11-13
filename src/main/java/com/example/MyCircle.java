package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyCircle extends MyComponent2D{
    public MyCircle(int x,int y){
        super(x,y);
        this.setName("圆");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(122,234,123));//需要进行设置
        g.drawOval(5,5,maxX-minX,maxY-minY);
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
                MyCircle myCircle= new MyCircle(e.getX(),e.getY());
                panel.add(myCircle);
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
                MyCircle myCircle = (MyCircle)panel.getComponent(panel.getComponentCount() - 1);
                myCircle.setX2Y2(e.getX(),e.getY());
            }
        });
        jFrame.add(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
