package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyPoint extends JComponent{
    private int x;
    private int y;
    private static int r=5;
    public enum Type{
        left,right,top,bottom,leftTop,rightTop,leftBottom,rightBottom,normal
    };
    private Type type=Type.normal;
    public MyPoint(){
        this.setLayout(null);
        this.setBounds(10,10,2*r,2*r);
    }
    public MyPoint(int x, int y){
        this.x=x-r;
        this.y=y-r;
        this.setLayout(null);
        this.setBounds(this.x,this.y,2*r,2*r);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                switch (type){
                    case leftBottom:
                        MyPoint.this.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                        break;
                    case leftTop:
                        MyPoint.this.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                        break;
                    case left:
                        MyPoint.this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                        break;
                    case top:
                        MyPoint.this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                        break;
                    case right:
                        MyPoint.this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                        break;
                    case bottom:
                        MyPoint.this.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                        break;
                    case rightTop:
                        MyPoint.this.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                        break;
                    case rightBottom:
                        MyPoint.this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                        break;
                    default:
                        MyPoint.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                switch (type){
                    case left: case right:
                        MyPoint.this.x+=e.getX();
                        break;
                    case bottom:case top:
                        MyPoint.this.y+=e.getY();
                        break;
                    default:
                        MyPoint.this.x+=e.getX();
                        MyPoint.this.y+=e.getY();
                }
                MyPoint.this.setBounds(MyPoint.this.x,MyPoint.this.y,2*r,2*r);
                MyPoint.this.repaint();
            }
        });
    }

    public void setType(MyPoint.Type t){
        this.type=t;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(new Color(255,1,255));
        g.fillOval(0,0,2*r,2*r);//相对于整个组件
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        Panel panel = new Panel();
        panel.setLayout(null);
        panel.setBounds(10,0,200,200);
        panel.setBackground(new Color(120,120,120));
        MyPoint myPoint = new MyPoint(100,100);
        panel.add(myPoint);
        jFrame.add(panel);
        jFrame.setBounds(0,0,1000,800);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
