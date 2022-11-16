package com.example.Component;

import com.example.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class CanvasPanel extends JPanel {

    private MyFrame frame = null;

    public void focusChanged() {
        Component[] components = this.getComponents();
        for (Component i : components) {
            if (i instanceof MyComponent) {
                MyComponent j = (MyComponent) i;
                j.lostFocus();
            }
        }
    }

    public CanvasPanel() {
        this.setBounds(0, 0, 970, 820);
        this.setBackground(new Color(255, 255, 255));
    }

    public void addListener(final MyComponent component) {
        component.addMyComponentEventListener(new MyComponent.MyComponentEventListener() {
            @Override
            public void remove(MyComponent myComponent) {
                CanvasPanel.this.remove(myComponent);
                CanvasPanel.this.repaint();
                CanvasPanel.this.focusChanged();
                CanvasPanel.this.requestFocus(true);
            }
        });
        final StringBuffer s = new StringBuffer();
        if (component instanceof MyText) {
            s.append("文本框");
        } else if (component instanceof MyRect) {
            s.append("直角矩形");
        } else if (component instanceof MyRoundRect) {
            s.append("圆角矩形");
        } else if (component instanceof MyCircle) {
            s.append("椭圆");
        } else if (component instanceof MyArrowHead) {
            s.append("箭头");
        } else if (component instanceof MyLine) {
            s.append("直线");
        }
        if(!s.toString().equals("")){
            component.setRightPanelChangeListener(new MyComponent.RightPanelChangeListener() {
                @Override
                public void rightPanelChangeL() {
                    CanvasPanel.this.frame.rightPanel.returnPanel(s.toString(), component);
                }
            });
        }
    }

    public CanvasPanel(MyFrame frame) {
        this.frame = frame;
        this.setBounds(0, 0, 970, 820);
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                CanvasPanel.this.frame.rightPanel.returnPanel("",null);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MyComponent component = null;
                super.mousePressed(e);
                if (CanvasPanel.this.frame.type != null) {
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
                            component = new MyLine(e.getX(), e.getY());
                            break;
                        case "画笔":
                            points.addLineSet();
                            break;
                    }
                    if (component != null) {
                        CanvasPanel.this.addListener(component);
                        CanvasPanel.this.add(component);
                    }
                }
                CanvasPanel.this.focusChanged();
                CanvasPanel.this.requestFocus(true);

            }
            @Override
            public void mouseReleased(MouseEvent e) {//鼠标松开时更新缩略图
                BufferedImage image = new BufferedImage(CanvasPanel.this.getWidth(), CanvasPanel.this.getHeight(), BufferedImage.SCALE_SMOOTH);
                Graphics2D g2 = (Graphics2D) image.getGraphics();
                CanvasPanel.this.paint(g2);
                MyJList jlist = CanvasPanel.this.frame.getJlist();
                jlist.updateImage(image);
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (CanvasPanel.this.frame.type == null) {
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
                        MyLine myLine = (MyLine) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myLine.setX2Y2(e.getX(), e.getY());
                        break;
                    case "画笔":
                        points.addPoint(new Point(e.getX(), e.getY()));
                        CanvasPanel.this.repaint();
                        break;
                    case "橡皮擦":
                        points.removeLine(new Point(e.getX(), e.getY()));
                        CanvasPanel.this.repaint();
                        break;
                }

            }

        });

    }

    static class Points {
        public LinkedList<LinkedList<Point>> lines = new LinkedList<>();

        void addPoint(Point p) {
            if (lines.size() > 0) {
                lines.getLast().add(p);
            }
        }
        void addLineSet(){
            lines.add(new LinkedList<Point>());
        }
       void removeLine(Point point){
            for(LinkedList<Point> line: lines){
                for(Point p:line){
                    if(p.x>=point.x-5&&p.x<=point.x+5&&p.y>=point.y-5&&p.y<=point.y+5){//在10*10的像素内存在点就删除
                        lines.remove(line);
                        return;
                    }
                }
            }
       }
    }

    public Points points = new Points();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0));
        BasicStroke stroke = new BasicStroke(2.0f);
        Graphics2D gr2 = (Graphics2D) g;
        gr2.setStroke(stroke);
        for(List<Point> line:points.lines){
            for(int i=1;i<line.size();++i){
                gr2.drawLine(line.get(i-1).x,line.get(i-1).y,line.get(i).x,line.get(i).y);
            }
        }
    }
}