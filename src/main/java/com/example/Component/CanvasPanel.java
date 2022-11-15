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
                    final MyText myText= new MyText(e.getX(),e.getY());
                    myText.setRightPanelChangeListener(new MyComponent.RightPanelChangeListener() {
                        @Override
                        public void rightPanelChangeL() {
                            CanvasPanel.this.frame.rightPanel.returnPanel("文字",myText);
                        }

                    });
                    CanvasPanel.this.add(myText);
                    CanvasPanel.this.focusChanged();
                }else if(CanvasPanel.this.frame.type.equals("直角矩形")){
                    final MyRect myRect= new MyRect(e.getX(),e.getY());
                    myRect.setRightPanelChangeListener(new MyComponent.RightPanelChangeListener() {
                        @Override
                        public void rightPanelChangeL() {
                            CanvasPanel.this.frame.rightPanel.returnPanel("直角矩形",myRect);
                        }
                    });
                    CanvasPanel.this.add(myRect);
                    CanvasPanel.this.focusChanged();
                }else if (CanvasPanel.this.frame.type.equals("圆角矩形")){
                    final MyRoundRect myRoundRect= new MyRoundRect(e.getX(),e.getY());
                    myRoundRect.setRightPanelChangeListener(new MyComponent.RightPanelChangeListener() {
                        @Override
                        public void rightPanelChangeL() {
                            CanvasPanel.this.frame.rightPanel.returnPanel("图形",myRoundRect);
                        }
                    });
                    CanvasPanel.this.add(myRoundRect);
                    CanvasPanel.this.focusChanged();
                }else if(CanvasPanel.this.frame.type.equals("椭圆")){
                    final MyCircle myCircle= new MyCircle(e.getX(),e.getY());
                    myCircle.setRightPanelChangeListener(new MyComponent.RightPanelChangeListener() {
                        @Override
                        public void rightPanelChangeL() {
                            CanvasPanel.this.frame.rightPanel.returnPanel("图形",myCircle);
                        }
                    });
                    CanvasPanel.this.add(myCircle);
                    CanvasPanel.this.focusChanged();
                }else if(CanvasPanel.this.frame.type.equals("箭头")){
                    final MyArrowHead myArrowHead= new MyArrowHead(e.getX(),e.getY());
                    myArrowHead.setRightPanelChangeListener(new MyComponent.RightPanelChangeListener() {
                        @Override
                        public void rightPanelChangeL() {
                            CanvasPanel.this.frame.rightPanel.returnPanel("图形",myArrowHead);
                        }
                    });
                    CanvasPanel.this.add(myArrowHead);
                    CanvasPanel.this.focusChanged();
                }else if(CanvasPanel.this.frame.type.equals("直线")){
                    final MyLine myLine=new MyLine(e.getX(),e.getY());
                    myLine.setRightPanelChangeListener(new MyComponent.RightPanelChangeListener() {
                        @Override
                        public void rightPanelChangeL() {
                            CanvasPanel.this.frame.rightPanel.returnPanel("线条",myLine);
                        }
                    });
                    CanvasPanel.this.add(myLine);
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
                }else if(CanvasPanel.this.frame.type.equals("直线")){
                    MyLine myLine = (MyLine)CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                    myLine.setX2Y2(e.getX(),e.getY());
                }

            }
        });

    }


}
