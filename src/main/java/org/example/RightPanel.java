package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel {
    private String panelType;
    public RightPanel(String type){
        panelType=type;
    }
    public static JButton getColorButton(){
        JButton color = new JButton("选择颜色");
        color.setBounds(100,80,100,20);
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser.showDialog(null,"颜色选择器",Color.red);
            }
        });
        return color;
    }
    public static JButton getFillColor(){
        JButton color = new JButton("颜色填充");
        color.setBounds(100,80,100,20);
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser.showDialog(null,"颜色选择",Color.red);
            }
        });
        return color;
    }
    public static JButton getBorderColor(){
        JButton color = new JButton("边界颜色");
        color.setBounds(100,120,100,20);
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser.showDialog(null,"颜色选择",Color.red);
            }
        });
        return color;
    }
    public static void getLineT(Panel rightPanel){
        JLabel lineType = new JLabel("线条类型");
        lineType.setBounds(100,160,50,20);
        String[] listLine = new String[]{"实线","虚线"};
        JComboBox<String> line= new JComboBox<>(listLine);
        line.setBounds(180,160,50,20);
        line.setSelectedIndex(0);
        rightPanel.add(lineType);
        rightPanel.add(line);
    }
    public static void getLineBold(Panel rightPanel){
        JLabel lineType = new JLabel("线条粗细");
        lineType.setBounds(100,200,50,20);
        String[] listLine = new String[]{"粗线","细线"};
        JComboBox<String> line= new JComboBox<>(listLine);
        line.setBounds(180,200,50,20);
        line.setSelectedIndex(0);
        rightPanel.add(lineType);
        rightPanel.add(line);
    }
    public static void getFontType(Panel rightPanel){
        JLabel fontType = new JLabel("字体类型");
        fontType.setBounds(100,120,50,20);
        String[] listType = new String[]{"宋体","黑体"};
        JComboBox<String> font= new JComboBox<>(listType);
        font.setBounds(180,120,50,20);
        font.setSelectedIndex(0);
        rightPanel.add(fontType);
        rightPanel.add(font);
    }
    public static void getFontSize(Panel rightPanel){
        JLabel fontSize = new JLabel("字体大小");
        fontSize.setBounds(100,160,50,20);
        String[] listFont = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
        JComboBox<String> font= new JComboBox<>(listFont);
        font.setBounds(180,160,50,20);
        font.setSelectedIndex(5);
        rightPanel.add(fontSize);
        rightPanel.add(font);
    }
    public Panel returnPanel(){
        Panel rightPanel = new Panel();
        rightPanel.setLayout(null);
        rightPanel.setPreferredSize(new Dimension(300,800));
        rightPanel.setBackground(new Color(255,255,200));
        if(panelType.equals("文字")){
            rightPanel.removeAll();
            JButton color=getColorButton();
            rightPanel.add(color);
            getFontSize(rightPanel);
            getFontType(rightPanel);
        }else if(panelType.equals("图形")){
            rightPanel.removeAll();
            JButton color=getFillColor();
            rightPanel.add(color);
            JButton color1=getBorderColor();
            rightPanel.add(color1);
        }else if(panelType.equals("线条")){
            rightPanel.removeAll();
            JButton color=getColorButton();
            rightPanel.add(color);
            getLineBold(rightPanel);
            getLineT(rightPanel);
        }else{
            rightPanel.removeAll();
        }
        return rightPanel;
    }
}



