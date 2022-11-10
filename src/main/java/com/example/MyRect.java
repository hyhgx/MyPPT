package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyRect extends JComponent {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int initX;
    private int initY;
    public MyRect(int x,int y){
        this.x1=x;
        this.x2=x;
        this.y1=y;
        this.y2=y;
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                MyRect.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                initX=e.getX();
                initY=e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                minX+=e.getX()-initX;
                minY+=e.getY()-initY;
                maxX+=e.getX()-initX;
                maxY+=e.getY()-initY;
                MyRect.this.setBounds(minX,minY,maxX-minX+10,maxY-minY+10);
                MyRect.this.repaint();
            }
        });
    }
    public void setX2Y2(int x,int y){
        this.x2=x;
        this.y2=y;
        minX=Math.min(x1,x2);
        minY=Math.min(y1,y2);
        maxX=Math.max(x1,x2);
        maxY=Math.max(y1,y2);
        this.setBounds(minX,minY,Math.abs(x2-x1)+10,Math.abs(y2-y1)+10);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(200,200,200));
        g.drawRect(0,0,maxX-minX,maxY-minY);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setBounds(0,0,1000,800);
        final Panel panel = new Panel();
        panel.setLayout(null);
        panel.setBounds(0,0,1000,700);
        panel.setBackground(new Color(123,123,123));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.out.println(000);
                MyRect myRect= new MyRect(e.getX(),e.getY());
                panel.add(myRect);
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
                MyRect myRect = (MyRect)panel.getComponent(panel.getComponentCount() - 1);
                myRect.setX2Y2(e.getX(),e.getY());
            }
        });
        jFrame.add(panel);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
