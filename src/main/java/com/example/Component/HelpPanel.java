package com.example.Component;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelpPanel extends JFrame{
        public HelpPanel(){
            this.setAlwaysOnTop(true);
            this.setResizable ( false );
            this.setTitle("帮助");
            this.setVisible(true);//从获取屏幕大小
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            int screenWidth= Toolkit.getDefaultToolkit().getScreenSize().width ;
            int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
            int winWidth=1500;
            int winHeight=800;
            int winx =( screenWidth - winWidth )/2; //
            int winY =( screenHeight- winHeight )/2;
            this.setBounds( winx +50, winY +50, winWidth , winHeight );
            JPanel panel1=new JPanel();
            panel1.setBounds(0, 0, 1500, 800);
            panel1.setLayout(null);
            panel1.setVisible(true);
            JScrollPane jsp = new JScrollPane();
            jsp.setBounds(0, 0, 1480, 780);
            JPanel panel2 = new JPanel();
            panel2.setPreferredSize(new Dimension(1480, 3000));
            panel2.setVisible(true);
            jsp.getViewport().add(panel2);
            jsp.validate();
            panel1.add(jsp);
            this.add(panel1);
            URL resource1 =this.getClass().getClassLoader().getResource("help/1.png");
            URL resource2 =this.getClass().getClassLoader().getResource("help/2.png");
            URL resource3 =this.getClass().getClassLoader().getResource("help/3.png");
            ImageIcon icon1 = new ImageIcon(resource1.getPath());
            JLabel j1=new JLabel(icon1);
            j1.setBounds(0,0,1480,1200);
            panel2.add(j1);
            ImageIcon icon2 = new ImageIcon(resource2.getPath());
            ImageIcon icon3 = new ImageIcon(resource3.getPath());
            JLabel j2=new JLabel(icon2);
            j2.setBounds(0,1200,780,1200);
            panel2.add(j2);
            JLabel j3=new JLabel(icon3);
            j3.setBounds(0,2400,1480,1000);
            panel2.add(j3);
            URL resource4 =this.getClass().getClassLoader().getResource("help/help.txt");
//
            String str="注意事项：\n" +
                    "1丶在另存为文件时，如果该PPT文件为打开以前创建的，本次文件将直接覆盖上一次文件，若为新建PPT，则会弹出选择路径的对话框。\n" +
                    "2丶由于插入图片时将会转化为Base64格式，透明背景图片不建议使用（颜色会改变）。  \n" +
                    "3丶橡皮擦只能擦除画的线条，若想删除图形请选中后按下deletel键。   ";
            JTextArea jta=new JTextArea(str,7,30);
            jta.setLineWrap(true);
            jta.setFont(new Font("宋体",Font.BOLD,16));
            jta.setBounds(0,4000,1000,1000);
            panel2.add(jta);
            jta.setEditable(false);
            panel2.setBackground(Color.white);
}}
