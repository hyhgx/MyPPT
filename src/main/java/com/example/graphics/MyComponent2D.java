package com.example.graphics;

import com.example.Component.CanvasPanel;
import com.example.Component.CanvasPanels;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyComponent2D extends MyComponent {
    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    protected int minX;
    protected int minY;
    protected int maxX;
    protected int maxY;
    public MyComponent2D(){//为了保存使用
        super();
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                MyComponent2D.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                initX=e.getX();
                initY=e.getY();
                getFocus();
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
                MyComponent2D.this.setBounds(minX-5,minY-5,maxX-minX+10,maxY-minY+10);
                MyComponent2D.this.repaint();
            }
        });
    }
    public MyComponent2D(int x,int y,Color lineColor){
        super();
        this.x1=x;
        this.x2=x;
        this.y1=y;
        this.y2=y;
        if(lineColor!=null){
            this.lineColor=lineColor;
        }
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                MyComponent2D.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                initX=e.getX();
                initY=e.getY();
                getFocus();
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
                MyComponent2D.this.setBounds(minX-5,minY-5,maxX-minX+10,maxY-minY+10);
                MyComponent2D.this.repaint();
            }
        });
    }

    @Override
    Point getRBPoint() {
        return new Point(maxX,maxY);
    }

    @Override
    Point getLTPoint() {
        return new Point(minX,minY);
    }

    @Override
    public void setX2Y2(int x,int y){
        this.x2=x;
        this.y2=y;
        minX=Math.min(x1,x2);
        minY=Math.min(y1,y2);
        maxX=Math.max(x1,x2);
        maxY=Math.max(y1,y2);
        this.setBounds(minX-5,minY-5,Math.abs(x2-x1)+10,Math.abs(y2-y1)+10);
        this.repaint();
    }
    @Override
    public void getFocus(){
        super.getFocus();
        CanvasPanel parent = (CanvasPanel)this.getParent();
        parent.focusChanged();
        if(myPoints.size()==0){
            MyPoint lT = new MyPoint(5, 5);
            lT.setType(MyPoint.Type.leftTop);
            MyPoint T = new MyPoint(5 + (maxX - minX) / 2, 5);
            T.setType(MyPoint.Type.top);
            MyPoint rT = new MyPoint(5 + (maxX - minX), 5);
            rT.setType(MyPoint.Type.rightTop);
            MyPoint l = new MyPoint(5, 5+(maxY-minY)/2);
            l.setType(MyPoint.Type.left);
            MyPoint lB = new MyPoint(5, 5+(maxY-minY));
            lB.setType(MyPoint.Type.leftBottom);
            MyPoint b = new MyPoint(5+ (maxX - minX) / 2, 5+(maxY-minY));
            b.setType(MyPoint.Type.bottom);
            MyPoint rB = new MyPoint(5+ (maxX - minX) ,5+(maxY-minY));
            rB.setType(MyPoint.Type.rightBottom);
            MyPoint r = new MyPoint(5+ (maxX - minX),5+(maxY-minY)/2);
            r.setType(MyPoint.Type.right);
            myPoints.add(lT);
            myPoints.add(T);
            myPoints.add(rT);
            myPoints.add(r);
            myPoints.add(rB);
            myPoints.add(b);
            myPoints.add(lB);
            myPoints.add(l);
            this.add(lT);
            this.add(T);
            this.add(rT);
            this.add(r);
            this.add(rB);
            this.add(b);
            this.add(lB);
            this.add(l);
            //绑定点的移动事件
            for (MyPoint myPoint : myPoints) {
                myPoint.addMyPointActionlistener(new MyPoint.MyPointActionlistener() {
                    @Override
                    public void myPointChangedPosition(MyPoint.Type t, int dx, int dy) {
                        switch (t) {
                            case top: {
                                changeMaxMin(0, dy, 0, 0);
                                break;
                            }
                            case bottom: {
                                changeMaxMin(0, 0, 0, dy);
                                break;
                            }
                            case left: {
                                changeMaxMin(dx, 0, 0, 0);
                                break;
                            }
                            case right: {
                                changeMaxMin(0, 0, dx, 0);
                                break;
                            }
                            case leftTop: {
                                changeMaxMin(dx, dy, 0, 0);
                                break;
                            }
                            case rightTop: {
                                changeMaxMin(0, dy, dx, 0);
                                break;
                            }
                            case rightBottom: {
                                changeMaxMin(0, 0, dx, dy);
                                break;
                            }
                            case leftBottom: {
                                changeMaxMin(dx, 0, 0, dy);
                                break;
                            }
                        }
                        updatePoints();
                        repaint();
                    }
                });
            }
        }else{
            for(MyPoint p:myPoints){
                p.setVisible(true);
            }
        }
        repaint();
    }
    @Override
    protected void updatePoints(){
        myPoints.get(0).setPoint(5,5,MyPoint.Type.leftTop);
        myPoints.get(1).setPoint(5 + (maxX - minX) / 2, 5,MyPoint.Type.top);
        myPoints.get(2).setPoint(5 + (maxX - minX), 5,MyPoint.Type.rightTop);
        myPoints.get(3).setPoint(5+(maxX - minX),5+(maxY-minY)/2,MyPoint.Type.right);
        myPoints.get(4).setPoint(5+(maxX - minX),5+(maxY-minY),MyPoint.Type.rightBottom);
        myPoints.get(5).setPoint(5+(maxX - minX)/2,5+(maxY-minY), MyPoint.Type.bottom);
        myPoints.get(6).setPoint(5,5+(maxY-minY), MyPoint.Type.leftBottom);
        myPoints.get(7).setPoint(5,5+(maxY-minY)/2, MyPoint.Type.left);
    }

    protected void changeMaxMin(int dMinX,int dMinY,int dMaxX,int dMaxY){
        minX+=dMinX;
        minY+=dMinY;
        maxX+=dMaxX;
        maxY+=dMaxY;
        updateMinMax();
        this.setBounds(minX-5,minY-5,maxX-minX+10,maxY-minY+10);
    }
    protected void updateMinMax(){
        int t1=minX;
        int t2=maxX;
        int t3=minY;
        int t4=maxY;
        minX=Math.min(t1,t2);
        maxX=Math.max(t1,t2);
        minY=Math.min(t3,t4);
        maxY=Math.max(t3,t4);
    }

    @Override
    public void moveComponent(int dx, int dy) {
        minX+=dx;
        minY+=dy;
        maxX+=dx;
        maxY+=dy;
        setBounds(minX-5,minY-5,maxX-minX+10,maxY-minY+10);
        repaint();
    }
}
