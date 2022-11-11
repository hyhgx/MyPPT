package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyRect extends JComponent {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int initX;
    private int initY;
    private ArrayList<MyPoint> myPoints=new ArrayList<>();
    public MyRect(int x,int y){
        this.x1=x;
        this.x2=x;
        this.y1=y;
        this.y2=y;
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("点击");
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println("释放");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                MyRect.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                initX=e.getX();
                initY=e.getY();
                getFocus();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                minX+=e.getX()-initX;
                minY+=e.getY()-initY;
                maxX+=e.getX()-initX;
                maxY+=e.getY()-initY;
                MyRect.this.setBounds(minX,minY,maxX-minX+10,maxY-minY+10);
                MyRect.this.repaint();
            }
        });
    }
    public void setX2Y2(int x,int y){
        this.x2=x;
        this.y2=y;
        minX=Math.min(x1,x2);
        minY=Math.min(y1,y2);
        maxX=Math.max(x1,x2);
        maxY=Math.max(y1,y2);
        this.setBounds(minX,minY,Math.abs(x2-x1)+10,Math.abs(y2-y1)+10);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(200,200,200));
        g.drawRect(5,5,maxX-minX,maxY-minY);
    }
    public void getFocus(){
        if(myPoints.size()==0){
            System.out.println("ll");
            MyPoint lT = new MyPoint(5, 5);

            lT.setType(MyPoint.Type.leftTop);
            MyPoint T = new MyPoint(5 + (maxX - minX) / 2, 5);
            T.setType(MyPoint.Type.top);
            MyPoint rT = new MyPoint(5 + (maxX - minX), 5);
            rT.setType(MyPoint.Type.rightTop);
            MyPoint l = new MyPoint(5, 5+(maxY-minY)/2);
            l.setType(MyPoint.Type.left);
            MyPoint lB = new MyPoint(5, 5+(maxY-minY));
            lB.setType(MyPoint.Type.leftBottom);
            MyPoint b = new MyPoint(5+ (maxX - minX) / 2, 5+(maxY-minY));
            b.setType(MyPoint.Type.bottom);
            MyPoint rB = new MyPoint(5+ (maxX - minX) ,5+(maxY-minY));
            rB.setType(MyPoint.Type.rightBottom);
            MyPoint r = new MyPoint(5+ (maxX - minX),5+(maxY-minY)/2);
            r.setType(MyPoint.Type.right);
            myPoints.add(lT);
            myPoints.add(T);
            myPoints.add(rT);
            myPoints.add(r);
            myPoints.add(rB);
            myPoints.add(b);
            myPoints.add(lB);
            myPoints.add(l);
            this.add(lT);
            this.add(T);
            this.add(rT);
            this.add(r);
            this.add(rB);
            this.add(b);
            this.add(lB);
            this.add(l);
        }
        repaint();
    }
    public void setPoints(){
        myPoints.get(0).setXY(5,5);
        myPoints.get(1).setXY(5 + (maxX - minX) / 2, 5);
        myPoints.get(2).setXY(5 + (maxX - minX), 5);
        myPoints.get(3).setXY(5, 5+(maxY-minY)/2);
        myPoints.get(4).setXY(5, 5+(maxY-minY));
        myPoints.get(5).setXY(5+ (maxX - minX) / 2, 5+(maxY-minY));
        myPoints.get(6).setXY(5+ (maxX - minX) ,5+(maxY-minY));
        myPoints.get(7).setXY(5+ (maxX - minX),5+(maxY-minY)/2);
        repaint();
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setBounds(0,0,1000,800);
        final Panel panel = new Panel();
        panel.setLayout(null);
        panel.setBounds(0,0,1000,700);
        panel.setBackground(new Color(123,123,123));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.out.println(000);
                MyRect myRect= new MyRect(e.getX(),e.getY());
                panel.add(myRect);
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
                MyRect myRect = (MyRect)panel.getComponent(panel.getComponentCount() - 1);
                myRect.setX2Y2(e.getX(),e.getY());
            }
        });


        jFrame.add(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
