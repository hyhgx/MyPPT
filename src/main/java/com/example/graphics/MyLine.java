package com.example.graphics;

import com.example.Component.CanvasPanel;
import com.example.Component.CanvasPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyLine extends MyComponent1D {
    public Color lineColor=new Color(200,200,200);
    public boolean lineT=false;
    public MyLine(int x,int y){
        super(x,y);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        float [] dash=new float[]{5,10};
        if(lineT){//虚线
            BasicStroke basicStroke = new BasicStroke(this.lineWidth,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER
                    ,10.0f,dash,0.0f);//设置画笔
            Graphics2D g1=(Graphics2D) g;
            g1.setStroke(basicStroke);
            g1.setColor(lineColor);
            g1.drawLine(getX1RelativePosition(),getY1RelativePosition(),getX2RelativePosition(),getY2RelativePosition());
        }else {//实线
            BasicStroke basicStroke1 = new BasicStroke(this.lineWidth);//设置画笔
            Graphics2D g2=(Graphics2D) g;
            g2.setStroke(basicStroke1);
            g2.setColor(lineColor);
            g.drawLine(getX1RelativePosition(),getY1RelativePosition(),getX2RelativePosition(),getY2RelativePosition());
        }
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
                MyLine myLine=new MyLine(e.getX(),e.getY());
                panel.add(myLine);
                panel.focusChanged();
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                MyLine myLine = (MyLine)panel.getComponent(panel.getComponentCount() - 1);
                myLine.setX2Y2(e.getX(),e.getY());
            }
        });
        jFrame.add(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
