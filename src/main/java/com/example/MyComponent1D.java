package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyComponent1D extends MyComponent {
    public MyComponent1D(int x, int y) {
        this.x1 = x;
        this.x2 = x;
        this.y1 = y;
        this.y2 = y;
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                initX = e.getX();
                initY = e.getY();
                //getFocus();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                MyComponent1D.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                x1 += e.getX() - initX;
                y1 += e.getY() - initY;
                x2 += e.getX() - initX;
                y2 += e.getY() - initY;
                MyComponent1D.this.setBounds(Math.min(x1, x2) - 5, Math.min(y1, y2) - 5, Math.abs(x1 - x2) + 10, Math.abs(y1 - y2) + 10);
                MyComponent1D.this.repaint();
            }
        });
    }

    @Override
    protected void setX2Y2(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        MyComponent1D.this.setBounds(Math.min(x1, x2) - 5, Math.min(y1, y2) - 5, Math.abs(x1 - x2) + 10, Math.abs(y1 - y2) + 10);
        MyComponent1D.this.repaint();
    }

    @Override
    public boolean contains(int x, int y) {
        if (x < 0 || x > this.getWidth() || y < 0 || y > this.getHeight()) {
            return false;
        }
        int x1 = this.x1 - (Math.min(this.x1, this.x2) - 5);
        int x2 = this.x2 - (Math.min(this.x1, this.x2) - 5);
        int y1 = this.y1 - (Math.min(this.y1, this.y2) - 5);
        int y2 = this.y2 - (Math.min(this.y1, this.y2) - 5);
        double k = ((double) (y2 - y1)) / (x2 - x1);
        double b = y2 - k * x2;
        double d = Math.abs(k * x + b - y) / Math.sqrt(k * k + 1);
        return d < 30;
    }
}
