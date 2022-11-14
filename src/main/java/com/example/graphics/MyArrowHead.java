package com.example.graphics;

import com.example.Component.CanvasPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyArrowHead extends MyComponent2D {
    public MyArrowHead(int x,int y){
        super(x,y);
        this.setName("箭头");
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(200,200,200));//后期进行设置
        g.drawRect(5,5+(maxY-minY)/4,(maxX-minX)/2,(maxY-minY)/2);
        int [] xpoint={5+(maxX-minX)/2,5+(maxX-minX)/2,5+(maxX-minX)};
        int [] ypoint={5,5+(maxY-minY),5+(maxY-minY)/2};
        g.drawPolygon(xpoint,ypoint,3);
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
