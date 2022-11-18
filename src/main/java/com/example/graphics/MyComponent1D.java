package com.example.graphics;

import com.example.Component.CanvasPanel;
import com.example.Component.CanvasPanels;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyComponent1D extends MyComponent {
    public MyComponent1D(int x, int y) {
        super();
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
                getFocus();
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
                updateBounds();
                MyComponent1D.this.repaint();
            }
        });
    }

    public MyComponent1D(){
        super();
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                initX = e.getX();
                initY = e.getY();
                getFocus();
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
                updateBounds();
                MyComponent1D.this.repaint();
            }
        });
    }

    @Override
    public void setX2Y2(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        updateBounds();
        MyComponent1D.this.repaint();
    }

    @Override
    public boolean contains(int x, int y) {
        if (x < 0 || x > this.getWidth() || y < 0 || y > this.getHeight()) {
            return false;
        }
        int x1 = getX1RelativePosition();
        int x2 = getX2RelativePosition();
        int y1 = getY1RelativePosition();
        int y2 = getY2RelativePosition();
        double k = ((double) (y2 - y1)) / (x2 - x1);
        double b = y2 - k * x2;
        double d = Math.abs(k * x + b - y) / Math.sqrt(k * k + 1);
        return d < 30;
    }

    @Override
    public void getFocus() {
        super.getFocus();
        CanvasPanel parent = (CanvasPanel) this.getParent();
        parent.focusChanged();
        if (myPoints.size() == 0) {
            MyPoint p1 = new MyPoint(getX1RelativePosition(), getY1RelativePosition());
            MyPoint p2 = new MyPoint(getX2RelativePosition(), getY2RelativePosition());
            p1.addMyPointActionlistener(new MyPoint.MyPointActionlistener() {
                @Override
                public void myPointChangedPosition(MyPoint.Type t, int dx, int dy) {
                    x1 += dx;
                    y1 += dy;
                    updateBounds();
                    updatePoints();
                    MyComponent1D.this.repaint();
                }
            });
            p2.addMyPointActionlistener(new MyPoint.MyPointActionlistener() {
                @Override
                public void myPointChangedPosition(MyPoint.Type t, int dx, int dy) {
                    x2 += dx;
                    y2 += dy;
                    updateBounds();
                    updatePoints();
                    MyComponent1D.this.repaint();
                }
            });
            myPoints.add(p1);
            myPoints.add(p2);
            this.add(p1);
            this.add(p2);
        } else {
            for (MyPoint p : myPoints) {
                p.setVisible(true);
            }
        }
        repaint();
    }

    public int getX1RelativePosition() {
        return x1 - (Math.min(x1, x2) - 5);
    }

    public int getX2RelativePosition() {
        return x2 - (Math.min(x1, x2) - 5);
    }

    public int getY1RelativePosition() {
        return y1 - (Math.min(y1, y2) - 5);
    }

    public int getY2RelativePosition() {
        return y2 - (Math.min(y1, y2) - 5);
    }

    public void updateBounds(){
        setBounds(Math.min(x1, x2) - 5, Math.min(y1, y2) - 5, Math.abs(x1 - x2) + 10, Math.abs(y1 - y2) + 10);
    }

    @Override
    protected void updatePoints() {
        myPoints.get(0).setXY(getX1RelativePosition(), getY1RelativePosition());
        myPoints.get(1).setXY(getX2RelativePosition(), getY2RelativePosition());
    }

    @Override
    public void moveComponent(int dx, int dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
        updateBounds();
        repaint();
    }

    @Override
    public void setPosition(int x, int y) {
        int dx=x-Math.min(x1,x2);
        int dy=y-Math.min(y1,y2);
        moveComponent(dx,dy);
    }
}
