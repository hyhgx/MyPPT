package com.example.graphics;

import com.example.Component.CanvasPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyArrowHead extends MyComponent2D {
    public MyArrowHead(int x,int y){
        super(x,y,null);
        this.setName("箭头");
    }
    public MyArrowHead(int x,int y,Color lineColor){
        super(x,y,lineColor);
        this.setName("箭头");
    }
    public MyArrowHead(int minX,int minY,int maxX,int maxY,String name, Color lineColor,Color fillcolor){
        super();
        this.fillColor=fillcolor;
        this.lineColor=lineColor;
        this.minX=minX;
        this.minY=minY;
        this.maxX=maxX;
        this.maxY=maxY;
        this.setName(name);
        this.setBounds(minX-5,minY-5,maxX-minX+10,maxY-minY+10);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BasicStroke basicStroke = new BasicStroke(1.0f);
        Graphics2D g1=(Graphics2D) g;
        g1.setStroke(basicStroke);
        g1.setColor(lineColor);
        g1.drawRect(5,5+(maxY-minY)/4,(maxX-minX)/2,(maxY-minY)/2);
        int [] xpoint={5+(maxX-minX)/2,5+(maxX-minX)/2,5+(maxX-minX)};
        int [] ypoint={5,5+(maxY-minY),5+(maxY-minY)/2};
        g1.drawPolygon(xpoint,ypoint,3);
        g1.setColor(fillColor);
        g1.fillRect(6,6+(maxY-minY)/4,(maxX-minX)/2-1,(maxY-minY)/2-1);
        int [] xpoint1={5+(maxX-minX)/2+1,5+(maxX-minX)/2+1,5+(maxX-minX-1)};
        int [] ypoint1={5+1,5+(maxY-minY)-1,5+(maxY-minY)/2};
        g1.fillPolygon(xpoint1,ypoint1,3);
    }

    @Override
    public MyComponent cloneMySelf() {
        return new MyArrowHead(minX,minY,maxX,maxY,getName(),lineColor,fillColor);
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
                MyArrowHead myArrowHead= new MyArrowHead(e.getX(),e.getY());
                panel.add(myArrowHead);
                panel.focusChanged();
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
                MyArrowHead myArrowHead = (MyArrowHead) panel.getComponent(panel.getComponentCount() - 1);
                myArrowHead.setX2Y2(e.getX(),e.getY());
            }
        });
        jFrame.add(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
