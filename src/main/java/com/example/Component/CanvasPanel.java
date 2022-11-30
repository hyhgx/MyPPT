package com.example.Component;

import com.example.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CanvasPanel extends JPanel {
    private boolean isAdd=false;//判断是否添加成功
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

    public void addListener(MyComponent component) {
        if(component instanceof MySelectArea){
            return;
        }
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
        if (!s.toString().equals("")) {
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
                CanvasPanel.this.frame.rightPanel.returnPanel("", null);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                switch (CanvasPanel.this.frame.type) {
                    case "文本框":
                        CanvasPanel.this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        break;
                    case "直角矩形":
                        CanvasPanel.this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        break;
                    case "圆角矩形":
                        CanvasPanel.this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        break;
                    case "椭圆":
                        CanvasPanel.this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        break;
                    case "箭头":
                        CanvasPanel.this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        break;
                    case "直线":
                        CanvasPanel.this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        break;
                    case "画笔":
                        Toolkit tk = Toolkit.getDefaultToolkit();
                        URL resourcex =this.getClass().getClassLoader().getResource("images/huabi.png");
                        Image image = new ImageIcon(resourcex).getImage();
                        Cursor cursor = tk.createCustomCursor(image, new Point(10, 10), "norm");
                        CanvasPanel.this.setCursor(cursor);
                        break;
                    case"橡皮擦":
                        Toolkit tk1 = Toolkit.getDefaultToolkit();
                        URL resource2 =this.getClass().getClassLoader().getResource("images/xiangpica.png");
                        Image image1 = new ImageIcon(resource2).getImage();
                        Cursor cursor1 = tk1.createCustomCursor(image1, new Point(10, 10), "norm");
                        CanvasPanel.this.setCursor(cursor1);
                        break;
                    default:
                        CanvasPanel.this.setCursor(new Cursor(0));
                        break;
                }
            }
        });



        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CanvasPanel.this.focusChanged();
                CanvasPanel.this.requestFocus(true);
                MyComponent component = null;
                super.mousePressed(e);
                if (CanvasPanel.this.frame.type != null) {
                    switch (CanvasPanel.this.frame.type) {
                        case "文本框":
                            component = new MyText(e.getX(), e.getY(), CanvasPanel.this.frame.out.getColor());
                            break;
                        case "直角矩形":
                            component = new MyRect(e.getX(), e.getY(), CanvasPanel.this.frame.out.getColor());
                            break;
                        case "圆角矩形":
                            component = new MyRoundRect(e.getX(), e.getY(), CanvasPanel.this.frame.out.getColor());
                            break;
                        case "椭圆":
                            component = new MyCircle(e.getX(), e.getY(), CanvasPanel.this.frame.out.getColor());
                            break;
                        case "箭头":
                            component = new MyArrowHead(e.getX(), e.getY(), CanvasPanel.this.frame.out.getColor());
                            break;
                        case "直线":
                            component = new MyLine(e.getX(), e.getY(), CanvasPanel.this.frame.out.getColor());
                            break;
                        case "画笔":
                            points.addLineSet();
                            break;
                        case "矩形选区":
                            component = new MySelectArea(e.getX(), e.getY());
                            break;
                    }
                    if (component != null) {
                        CanvasPanel.this.addListener(component);
                        CanvasPanel.this.add(component);
                        if(component instanceof MySelectArea){//如果是矩形选区,设置事件触发优先级最高且不被自动删除
                            CanvasPanel.this.setComponentZOrder(component,0);
                            CanvasPanel.this.isAdd=false;
                        }else{
                            CanvasPanel.this.isAdd=true;
                        }

                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                //鼠标松开时更新缩略图
                updateLeftImage();
                //如果是矩形选区,松手时自动聚焦
                if(CanvasPanel.this.frame.type.equals("矩形选区")){
                    Component component = CanvasPanel.this.getComponent(0);
                    if(component instanceof MySelectArea){
                        MySelectArea selectArea = (MySelectArea)component;
                        selectArea.getFocus();
                    }
                }
                //松开时删除无效图形
                if(CanvasPanel.this.isAdd){
                    int lastIndex=CanvasPanel.this.getComponentCount() - 1;
                    if( lastIndex>=0){
                        MyComponent component=(MyComponent) CanvasPanel.this.getComponent(lastIndex);
                        if(  component.isUseful==false){
                            CanvasPanel.this.remove(CanvasPanel.this.getComponentCount() - 1);
                            CanvasPanel.this.isAdd=false;
                        }
                    }

                }
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
                        myText.isUseful=true;
                        break;
                    case "直角矩形":
                        MyRect myRect = (MyRect) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myRect.setX2Y2(e.getX(), e.getY());
                        myRect.isUseful=true;
                        break;
                    case "圆角矩形":
                        MyRoundRect myRoundRect = (MyRoundRect) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myRoundRect.setX2Y2(e.getX(), e.getY());
                        myRoundRect.isUseful=true;
                        break;
                    case "椭圆":
                        MyCircle myCircle = (MyCircle) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myCircle.setX2Y2(e.getX(), e.getY());
                        myCircle.isUseful=true;
                        break;
                    case "箭头":
                        MyArrowHead myArrowHead = (MyArrowHead) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myArrowHead.setX2Y2(e.getX(), e.getY());
                        myArrowHead.isUseful=true;
                        break;
                    case "直线":
                        MyLine myLine = (MyLine) CanvasPanel.this.getComponent(CanvasPanel.this.getComponentCount() - 1);
                        myLine.setX2Y2(e.getX(), e.getY());
                        myLine.isUseful=true;
                        break;
                    case "画笔":
                        points.addPoint(new Point(e.getX(), e.getY()));
                        CanvasPanel.this.repaint();
                        break;
                    case "橡皮擦":
                        points.removeLine(new Point(e.getX(), e.getY()));
                        CanvasPanel.this.repaint();
                        break;
                    case "矩形选区":
                        MySelectArea selectArea = (MySelectArea) CanvasPanel.this.getComponent(0);
                        selectArea.setX2Y2(e.getX(), e.getY());
                        break;
                }

            }

        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按下Ctrl+v
                if (e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK && e.getKeyCode() == 'V') {
                    handlePaste();
                }
            }
        });

    }

    class Points {
        public LinkedList<LinkedList<Point>> lines = new LinkedList<>();
        public LinkedList<Color> colors = new LinkedList<>();

        void addPoint(Point p) {
            if (lines.size() > 0) {
                lines.getLast().add(p);
            }
        }

        public void setLines(LinkedList<LinkedList<Point>> lines){
            this.lines=lines;
        }
        public void setColors(LinkedList<Color> colors){
            this.colors=colors;
        }

        void addLineSet() {
            lines.add(new LinkedList<Point>());
            colors.add(CanvasPanel.this.frame.out.getColor());
        }

        void removeLine(Point point) {
            for (LinkedList<Point> line : lines) {
                for (Point p : line) {
                    if (p.x >= point.x - 5 && p.x <= point.x + 5 && p.y >= point.y - 5 && p.y <= point.y + 5) {//在10*10的像素内存在点就删除
                        int i = lines.indexOf(line);
                        colors.remove(i);
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
        int index = 0;
        for (List<Point> line : points.lines) {
            gr2.setColor(points.colors.get(index));
            for (int i = 1; i < line.size(); ++i) {
                gr2.drawLine(line.get(i - 1).x, line.get(i - 1).y, line.get(i).x, line.get(i).y);
            }
            index++;
        }
    }

    public List<MyComponent> getComponentsInArea(Rectangle rectangle){
        ArrayList<MyComponent> list=new ArrayList<>();
        Component[] components = this.getComponents();
        for (Component i : components) {
            if (i instanceof MyComponent) {
                MyComponent myComponent=(MyComponent) i;
                if(myComponent.isInArea(rectangle)){
                    list.add(myComponent);
                }
            }
        }
        return list;
    }

    public void handlePaste(){
        if(MyClipBoard.getContent().isEmpty()){
            return;
        }
        ArrayList<MyComponent> contents = MyClipBoard.getContent();
        if(MyClipBoard.isSingle){
            MyComponent myComponent = contents.get(0);
            MyComponent contentCloned = myComponent.cloneMySelf();//以剪切板中的为副本clone一个新的
            CanvasPanel.this.addListener(contentCloned);
            CanvasPanel.this.add(contentCloned);
            CanvasPanel.this.repaint();
            return;
        }
        MySelectArea area = (MySelectArea) contents.get(0).cloneMySelf();
        CanvasPanel.this.addListener(area);
        CanvasPanel.this.add(area);
        CanvasPanel.this.setComponentZOrder(area,0);
        ArrayList<MyComponent> list=new ArrayList<>();
        for(int i=1;i<contents.size();++i){
            MyComponent component=contents.get(i).cloneMySelf();
            CanvasPanel.this.addListener(component);
            CanvasPanel.this.add(component);
            list.add(component);
        }
        area.setComponentsSelected(list);
        area.getFocus();
        this.repaint();
        updateLeftImage();
    }

    public void updateLeftImage(){
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.SCALE_SMOOTH);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        this.paint(g2);
        MyJList jlist = this.frame.getJlist();
        jlist.updateImage(image,this);
    }
}
