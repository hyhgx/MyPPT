package com.example.graphics;

import com.example.graphics.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MyComponent extends JComponent {
    public boolean isUseful=false;
    public Color fillColor=new Color(255,255,255,0);
    public Color lineColor=new Color(0,0,0);
    public float lineWidth=1.0f;
    protected int x1;
    protected int x2;
    protected int y1;
    protected int y2;
    protected int initX;
    protected int initY;
    protected ArrayList<MyPoint> myPoints=new ArrayList<>();
    protected MyComponentEventListener eventListener=null;

    Point getLTPoint(){//左上角
        return new Point(Math.min(x1,x2),Math.min(y1,y2));
    }
    Point getRBPoint(){//右下角
        return new Point(Math.max(x1,x2),Math.max(y1,y2));
    }
    public void addMyComponentEventListener(MyComponentEventListener eventListener){
        this.eventListener=eventListener;
    }
    public MyComponent(){
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==127&&eventListener!=null){//按下delete键时
                    eventListener.remove(MyComponent.this);
                }
            }
        });
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public void setX2Y2(int x,int y){}
    public void lostFocus(){
        if(myPoints.size()!=0){
            for(MyPoint p:myPoints){
                p.setVisible(false);
            }
        }
    }

    public interface MyComponentEventListener{
        void remove(MyComponent myComponent);

    }

    public interface RightPanelChangeListener{
        void rightPanelChangeL();
    }
    public RightPanelChangeListener rightPanelChangeListener;
    public void setRightPanelChangeListener(RightPanelChangeListener r){
        this.rightPanelChangeListener=r;
    }
    public void getFocus(){
        this.requestFocus(true);
        if(rightPanelChangeListener!=null){
            rightPanelChangeListener.rightPanelChangeL();
        }
    }
    protected void updatePoints(){}

    public Boolean isInArea(Rectangle rectangle){
        Point locat = this.getLocation();

        if(this.myPoints.size()!=0){//有操作点时,检验操作点是否全部在区域内
            for(MyPoint p:this.myPoints){
                if(!(rectangle.contains(new Point(p.x+locat.x,p.y+locat.y)))){
                    return false;
                }
            }
        }else {//无操作点时检验x1,y1,x2,y2是否在区域内
            return rectangle.contains(this.getLTPoint())&&rectangle.contains(this.getRBPoint());
        }
        return true;
    }

    public void moveComponent(int dx,int dy){}
}
