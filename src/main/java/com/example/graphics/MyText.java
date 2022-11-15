package com.example.graphics;

import com.example.Component.CanvasPanel;
import com.example.Component.CanvasPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MyText extends MyComponent2D {
    public JTextArea text=new JTextArea(20,20);
    public JPopupMenu jp = new JPopupMenu();
    public JMenuItem copy= new JMenuItem("复制");
    public JMenuItem paste = new JMenuItem("粘贴");
    public JMenuItem cut = new JMenuItem("剪切");
    public JMenuItem selectAll = new JMenuItem("全选");
    public MyText(int x,int y){
       super(x,y);
       jp.add(copy);
       jp.add(cut);
       jp.add(paste);
       jp.add(selectAll);
       this.text.setFont(new Font("宋体",Font.PLAIN,12));
       this.font=this.text.getFont();
       this.setName("文本框");
       this.add(text);
       this.text.setOpaque(false);
       this.text.setLineWrap(true);
       this.text.setWrapStyleWord(true);
       this.text.setBorder(BorderFactory.createEtchedBorder());
       this.setBackground(new Color(123,123,123));
       this.text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                MyText.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));//改变光标
            }

           @Override
           public void mouseClicked(MouseEvent e) {
               super.mouseClicked(e);
               if(e.getButton()==MouseEvent.BUTTON3){
                   MyText.this.jp.show(MyText.this,e.getX(),e.getY());
               }
           }
        });

        this.copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyText.this.text.copy();
            }
        });
        this.paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyText.this.text.paste();
            }
        });
        this.cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyText.this.text.cut();
            }
        });

        this.selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyText.this.text.selectAll();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.text.setFont(this.font);
        this.text.setBounds(5,5,maxX-minX,maxY-minY);
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
                MyText myText= new MyText(e.getX(),e.getY());
                panel.add(myText);
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
                MyText myText = (MyText) panel.getComponent(panel.getComponentCount() - 1);
                myText.setX2Y2(e.getX(),e.getY());
            }
        });
        jFrame.add(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }
}
