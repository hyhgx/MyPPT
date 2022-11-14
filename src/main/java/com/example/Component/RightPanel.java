package com.example.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RightPanel {
    private String panelType;
    public RightPanel(String type){
        panelType=type;
    }
    public static JButton getColorButton(){
        JButton color = new JButton();
        color.setBounds(140,50,50,50);
        String ppath="src/main/resources/images/change.png";
        ImageIcon iicon =new ImageIcon(ppath);
        Image ttemp=iicon.getImage().getScaledInstance(color.getWidth(),color.getHeight(),iicon.getImage().SCALE_AREA_AVERAGING);
        iicon=new ImageIcon(ttemp);
        color.setIcon(iicon);
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
    public static void getLineT(JPanel rightPanel){
        JLabel lineType = new JLabel("线条类型");
        lineType.setBounds(100,160,50,20);
        String[] listLine = new String[]{"实线","虚线"};
        JComboBox<String> line= new JComboBox<>(listLine);
        line.setEditable(true);
        line.setBounds(180,160,50,20);
        line.setSelectedIndex(0);
        rightPanel.add(lineType);
        rightPanel.add(line);
    }
    public static void getLineBold(JPanel rightPanel){
        JLabel lineType = new JLabel("线条粗细");
        lineType.setBounds(100,200,50,20);
        String[] listLine = new String[]{"粗线","细线"};
        JComboBox<String> line= new JComboBox<>(listLine);
        line.setEditable(true);
        line.setBounds(180,200,50,20);
        line.setSelectedIndex(0);
        rightPanel.add(lineType);
        rightPanel.add(line);
    }
    public static void getFontType(JPanel rightPanel){
        JLabel fontType = new JLabel("字体类型");
        fontType.setBounds(100,120,50,20);
        String[] listType = new String[]{"宋体","黑体"};
        final JComboBox<String> font= new JComboBox<>(listType);
        font.setEditable(true);
        font.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=font.getSelectedIndex();
                    Object selectedItem = font.getSelectedItem();
                }
            }
        });
        font.setBounds(180,120,50,20);
        font.setSelectedIndex(0);
        rightPanel.add(fontType);
        rightPanel.add(font);
    }
    public static void getFontSize(JPanel rightPanel){
        JLabel fontSize = new JLabel("字体大小");
        fontSize.setBounds(100,160,50,20);
        String[] listFont = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
        JComboBox<String> font= new JComboBox<>(listFont);
        font.setEditable(true);
        font.setBounds(180,160,50,20);
        font.setSelectedIndex(5);
        rightPanel.add(fontSize);
        rightPanel.add(font);
    }
    public JPanel returnPanel(){
        Image image=new ImageIcon("src/main/resources/images/img.png").getImage();
        BackgroundPanel rightPanel = new BackgroundPanel(image);
        rightPanel.setLayout(null);
        rightPanel.setPreferredSize(new Dimension(300,800));
        //rightPanel.setBackground(new Color(255,255,200));

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




