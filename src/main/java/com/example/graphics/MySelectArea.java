package com.example.graphics;

import com.example.Component.CanvasPanel;
import com.example.Component.MyClipBoard;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MySelectArea extends MyComponent2D {
    private boolean isCloned=false;//是否是被克隆生成的,如果是,则在聚焦时不需要绑定在区域内的组件
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
        isCloned=true;
        this.setBounds(minX - 5, minY - 5, maxX - minX + 10, maxY - minY + 10);
        init();
    }

    private final ArrayList<MyComponent> componentsSelected=new ArrayList<>();

    private void init() {
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(!isCloned){
                    CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
                    componentsSelected.addAll(parent.getComponentsInArea(new Rectangle(minX, minY, maxX - minX, maxY - minY)));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
                if (parent != null) {
                    parent.remove(MySelectArea.this);
                    parent.repaint();
                    parent.updateLeftImage();
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 127) {//按下delete键时
                    handleDelete();
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                for (MyComponent i : componentsSelected) {
                    i.moveComponent(e.getX() - initX, e.getY() - initY);
                }
            }
        });
    }

    @Override
    public MyComponent cloneMySelf() {
        return new MySelectArea(minX,maxX,minY,maxY);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        float[] dash = new float[]{5, 10};
        BasicStroke basicStroke = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER
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
        for (MyComponent i : componentsSelected) {
            parent.remove(i);
        }
        parent.remove(MySelectArea.this);
        parent.repaint();
        parent.updateLeftImage();
    }

    @Override
    public void handleCopy() {
        ArrayList<MyComponent> myComponents = new ArrayList<>();
        CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
        MySelectArea mySelectAreaCloned = (MySelectArea) this.cloneMySelf();
        mySelectAreaCloned.setPosition(20,20);
        myComponents.add(mySelectAreaCloned);
        for (MyComponent i : componentsSelected) {
            MyComponent myComponent = i.cloneMySelf();
            myComponent.setPosition(myComponent.getLocation().x-this.getLocation().x+20,myComponent.getLocation().y-this.getLocation().y+20);
            myComponents.add(myComponent);
        }
        MyClipBoard.setContent(myComponents);
    }

    @Override
    public void handleCut() {
        handleCopy();
        CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
        parent.requestFocus();
        handleDelete();
        parent.updateLeftImage();
    }


    @Override
    protected void updateMinMax() {
        super.updateMinMax();
        componentsSelected.clear();
        CanvasPanel parent = (CanvasPanel) MySelectArea.this.getParent();
        componentsSelected.addAll(parent.getComponentsInArea(new Rectangle(minX, minY, maxX - minX, maxY - minY)));
    }

    public void setComponentsSelected(List<MyComponent> components){
        componentsSelected.addAll(components);
    }

}
