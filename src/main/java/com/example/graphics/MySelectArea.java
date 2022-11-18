package com.example.graphics;

import com.example.Component.CanvasPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MySelectArea extends MyComponent2D {
    public MySelectArea(int x, int y) {
        super(x, y, null);
        init();
    }

    public MySelectArea(int minX, int maxX, int minY, int maxY) {
        super(0, 0, null);
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.setBounds(minX - 5, minY - 5, maxX - minX + 10, maxY - minY + 10);
        init();
    }

    private void init() {
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
                if (parent != null) {
                    parent.remove(MySelectArea.this);
                    parent.repaint();
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 127) {//按下delete键时
                    handleDelete();
                }else if (e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK && e.getKeyCode() == 'C') {// 按下CTRL+C
                    handleCopy();
                } else if (e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK && e.getKeyCode() == 'V') { //按下Ctrl+v,转发给外层
//                    CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
//                    parent.handlePaste();
                }else if(e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK && e.getKeyCode() == 'X'){//按下Ctrl+x
                    handleCut();
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
                List<MyComponent> componentsInArea = parent.getComponentsInArea(new Rectangle(minX, minY, maxX - minX, maxY - minY));
                for (MyComponent i : componentsInArea) {
                    i.moveComponent(e.getX() - initX, e.getY() - initY);
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        float[] dash = new float[]{5, 10};
        BasicStroke basicStroke = new BasicStroke(this.lineWidth, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER
                , 10.0f, dash, 0.0f);//设置画笔
        Graphics2D g1 = (Graphics2D) g;
        g1.setStroke(basicStroke);

        g1.setColor(new Color(28, 28, 28));
        g1.drawRect(5, 5, maxX - minX, maxY - minY);
        g1.setColor(new Color(185, 185, 185, 128));
        g1.fillRect(6, 6, maxX - minX - 2, maxY - minY - 2);
    }

    public void handleDelete() {
        CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
        List<MyComponent> componentsInArea = parent.getComponentsInArea(new Rectangle(minX, minY, maxX - minX, maxY - minY));
        for (MyComponent i : componentsInArea) {
            parent.remove(i);
        }
        parent.remove(MySelectArea.this);
        parent.repaint();
    }

    @Override
    public void handleCopy() {
//        ArrayList<MyComponent> myComponents = new ArrayList<>();
//        CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
//        List<MyComponent> componentsInArea = parent.getComponentsInArea(new Rectangle(minX, minY, maxX - minX, maxY - minY));
//        for (MyComponent i : componentsInArea) {
//            MyComponent myComponent = i.cloneMySelf();
//        }
    }
}
