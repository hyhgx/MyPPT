package com.example.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class ViewFrame extends JFrame {
    ViewPanel viewPanel = new ViewPanel();
    public List<CanvasPanel> panels;
    public BufferedImage image;
    public CanvasPanel currentPanel=new CanvasPanel();
    public int index=0;
    public ViewFrame(int beginIndex,MyFrame myFrame){
        this.viewPanel.setBounds(268, 50, 970, 820);
        this.index=beginIndex;
        this.setUndecorated(true);
        this.setLayout(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("播放");
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    ViewFrame.this.setVisible(false);
                    myFrame.setVisible(true);
                }
            }
        });
        this.currentPanel.setBounds(0, 0, 970, 820);
        this.panels=myFrame.panels.returnPanels();
        this.add(viewPanel);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton()==MouseEvent.BUTTON1){
                    if(index>0){
                        index-=1;
                    }
                }else if(e.getButton()==MouseEvent.BUTTON3){
                    if(index<ViewFrame.this.panels.size()-1){
                        index+=1;
                    }
                }
                ViewFrame.this.repaint();

            }
        });
    }
    public void drawCurrentImage(){
        this.currentPanel=panels.get(index);
        this.image = new BufferedImage(currentPanel.getWidth(), currentPanel.getHeight(), BufferedImage.SCALE_SMOOTH);
        Graphics2D g2d = image.createGraphics();
        this.currentPanel.paint(g2d);
        g2d.dispose();
        this.viewPanel.setImage(image);
        this.viewPanel.repaint();
    }

    @Override
    public void repaint() {
        super.repaint();
        drawCurrentImage();
    }
}

