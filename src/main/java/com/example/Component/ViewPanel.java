package com.example.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPanel extends JPanel {
    public BufferedImage image;
    public ViewPanel(){
        this.setBounds(0, 0, 970, 820);
        this.setVisible(true);
    }
    public void setImage(BufferedImage image){
        this.image=image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image,0,0,null);
    }
}
