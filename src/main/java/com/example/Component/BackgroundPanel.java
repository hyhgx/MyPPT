package com.example.Component;

 import java.awt.*;
 import javax.swing.*;

public class BackgroundPanel extends JPanel {
private static final long serialVersionUID = -6352788025440244338L;
private Image image = new ImageIcon("src/main/resources/images/img.png").getImage();

public BackgroundPanel() {
}// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
 protected void paintComponent(Graphics g) {
 g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
 }
}