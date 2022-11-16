package com.example.Component;

import com.example.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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

    public void addListener(final MyComponent component){
        component.addMyComponentEventListener(new MyComponent.MyComponentEventListener() {
            @Override
            public void remove(MyComponent myComponent) {
                CanvasPanel.this.remove(myComponent);
                CanvasPanel.this.repaint();
            }
        });
        final StringBuffer s=new StringBuffer();
        if(component instanceof MyText){
            s.append("文本框");
        }else if(component instanceof MyRect){
            s.append("直角矩形");
        }else if(component instanceof MyRoundRect){
            s.append("圆角矩形");
        }else if(component instanceof MyCircle){
            s.append("椭圆");
        }else if(component instanceof MyArrowHead){
            s.append("箭头");
        }else if(component instanceof MyLine){
            s.append("直线");
        }
        component.setRightPanelChangeListener(new MyComponent.RightPanelChangeListener() {
            @Override
            public void rightPanelChangeL() {
                CanvasPanel.this.frame.rightPanel.returnPanel(s.toString(),component);
            }
        });
    }
    public CanvasPanel(MyFrame frame){
        this.frame=frame;
        this.setBounds(0,0,970,820);
        this.setBackground(new Color(255,255,255));
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MyComponent component=null;
                super.mousePressed(e);
                if(CanvasPanel.this.frame.type!=null){
                    switch (CanvasPanel.this.frame.type) {
                        case "文本框":
                            component = new MyText(e.getX(), e.getY());
                            break;
                        case "直角矩形":
                            component = new MyRect(e.getX(), e.getY());
                            break;
                        case "圆角矩形":
                            component = new MyRoundRect(e.getX(), e.getY());
                            break;
                        case "椭圆":
                            component = new MyCircle(e.getX(), e.getY());
                            break;
                        case "箭头":
                            component = new MyArrowHead(e.getX(), e.getY());
                            break;
                        case "直线":
                            component=new MyLine(e.getX(),e.getY());
                            break;
                    }
                    if(component!=null){
                        CanvasPanel.this.addListener(component);
                        CanvasPanel.this.add(component);
                    }
                }
                CanvasPanel.this.focusChanged();
                CanvasPanel.this.requestFocus(true);

            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(CanvasPanel.this.frame.type==null){
                    return;
                }
                switch (CanvasPanel.this.frame.type) {
                    case "文本框":
                        MyText myText = (MyText) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myText.setX2Y2(e.getX(), e.getY());
                        break;
                    case "直角矩形":
                        MyRect myRect = (MyRect) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myRect.setX2Y2(e.getX(), e.getY());
                        break;
                    case "圆角矩形":
                        MyRoundRect myRoundRect = (MyRoundRect) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myRoundRect.setX2Y2(e.getX(), e.getY());
                        break;
                    case "椭圆":
                        MyCircle myCircle = (MyCircle) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myCircle.setX2Y2(e.getX(), e.getY());
                        break;
                    case "箭头":
                        MyArrowHead myArrowHead = (MyArrowHead) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myArrowHead.setX2Y2(e.getX(), e.getY());
                        break;
                    case "直线":
                        MyLine myLine = (MyLine)CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myLine.setX2Y2(e.getX(),e.getY());
                        break;
                }

            }
        });
        this.addMouseListener(new MouseAdapter() {//设置按压就更新缩略图
            @Override
            public void mousePressed(MouseEvent e) {
                BufferedImage image = new BufferedImage(CanvasPanel.this.getWidth(),CanvasPanel.this.getHeight(), BufferedImage.SCALE_SMOOTH);
                Graphics2D g2 =(Graphics2D) image.getGraphics();
                CanvasPanel.this.paint(g2);
                MyJList jlist = CanvasPanel.this.frame.getJlist();
                jlist.updateImage(image);
            }
        });

    }


}
