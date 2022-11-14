package com.example.Component;

import com.example.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public  class CanvasPanel extends JPanel {

    private MyFrame frame=null;
    public void focusChanged(){
        Component[] components = this.getComponents();
        for(Component i: components){
            if(i instanceof MyComponent){
                MyComponent j=(MyComponent) i;
                j.lostFocus();
            }
        }
    }
    public CanvasPanel(){
        this.setBounds(0,0,970,820);
        this.setBackground(new Color(255,255,255));
    }
    public CanvasPanel(MyFrame frame){
        this.frame=frame;
        this.setBounds(0,0,970,820);
        this.setBackground(new Color(255,255,255));
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.out.println(CanvasPanel.this.frame.type);
                if(CanvasPanel.this.frame.type.equals("文本框")){
                    MyText myText= new MyText(e.getX(),e.getY());
                    CanvasPanel.this.add(myText);
                    CanvasPanel.this.focusChanged();
                }else if(CanvasPanel.this.frame.type.equals("直角矩形")){
                    MyRect myRect= new MyRect(e.getX(),e.getY());
                    CanvasPanel.this.add(myRect);
                    CanvasPanel.this.focusChanged();
                }else if (CanvasPanel.this.frame.type.equals("圆角矩形")){
                    MyRoundRect myRoundRect= new MyRoundRect(e.getX(),e.getY());
                    CanvasPanel.this.add(myRoundRect);
                    CanvasPanel.this.focusChanged();
                }else if(CanvasPanel.this.frame.type.equals("椭圆")){
                    MyCircle myCircle= new MyCircle(e.getX(),e.getY());
                    CanvasPanel.this.add(myCircle);
                    CanvasPanel.this.focusChanged();
                }else if(CanvasPanel.this.frame.type.equals("箭头")){
                    MyArrowHead myArrowHead= new MyArrowHead(e.getX(),e.getY());
                    CanvasPanel.this.add(myArrowHead);
                    CanvasPanel.this.focusChanged();
                }

            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(CanvasPanel.this.frame.type.equals("文本框")){
                    MyText myText = (MyText) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                    myText.setX2Y2(e.getX(),e.getY());
                }else if(CanvasPanel.this.frame.type.equals("直角矩形")){
                    MyRect myRect = (MyRect)CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                    myRect.setX2Y2(e.getX(),e.getY());
                }else if (CanvasPanel.this.frame.type.equals("圆角矩形")){
                    MyRoundRect myRoundRect = (MyRoundRect) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                    myRoundRect.setX2Y2(e.getX(),e.getY());
                }else if(CanvasPanel.this.frame.type.equals("椭圆")){
                    MyCircle myCircle = (MyCircle) CanvasPanel.this.getComponent( CanvasPanel.this.getComponentCount() - 1);
                    myCircle.setX2Y2(e.getX(),e.getY());
                }else if(CanvasPanel.this.frame.type.equals("箭头")){
                    MyArrowHead myArrowHead = (MyArrowHead) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                    myArrowHead.setX2Y2(e.getX(),e.getY());
                }

            }
        });

    }


}
