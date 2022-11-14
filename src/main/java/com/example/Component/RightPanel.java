package com.example.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RightPanel {
    private String panelType;
    private Component component;
    public RightPanel(String type){
        panelType=type;
    }
    public RightPanel(Component component,String type){
        this.component=component;
        this.panelType=type;
    }
    public JButton getColorButton(){
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
                Color returncolor = JColorChooser.showDialog(null, "颜色选择器", Color.red);
                RightPanel.this.component.setBackground(returncolor);
            }
        });
        return color;
    }
    public  JButton getFillColor(){
        JButton color = new JButton("颜色填充");
        color.setBounds(100,80,100,20);
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RightPanel.this.component.setBackground(JColorChooser.showDialog(null,"颜色选择",Color.red));
            }
        });
        return color;
    }
    public  JButton getBorderColor(){
        JButton color = new JButton("边界颜色");
        color.setBounds(100,120,100,20);
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RightPanel.this.component.setBackground(JColorChooser.showDialog(null,"颜色选择",Color.red));
            }
        });
        return color;
    }
    public  void getLineT(JPanel rightPanel,Component component){
        JLabel lineType = new JLabel("线条类型");
        lineType.setBounds(100,160,50,20);
        String[] listLine = new String[]{"实线","虚线"};
        final JComboBox<String> line= new JComboBox<>(listLine);
        line.setEditable(true);
        line.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=line.getSelectedIndex();
                    Object selectedItem = line.getSelectedItem();

                }
            }
        });
        line.setBounds(180,160,50,20);
        line.setSelectedIndex(0);
        rightPanel.add(lineType);
        rightPanel.add(line);
    }
    public  void getLineBold(JPanel rightPanel,Component component){
        JLabel lineType = new JLabel("线条粗细");
        lineType.setBounds(100,200,50,20);
        String[] listLine = new String[]{"粗线","细线"};
        final JComboBox<String> line= new JComboBox<>(listLine);
        line.setEditable(true);
        line.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=line.getSelectedIndex();
                    Object selectedItem = line.getSelectedItem();
                    int s=0;
                    if(selectedItem.equals("加粗")){
                        s=1;
                    }
//                    RightPanel.this.component.setFont(new Font(RightPanel.this.component.getFont().getName(),
//                            s,
//                            RightPanel.this.component.getFont().getSize()));
                }
            }
        });
        line.setBounds(180,200,50,20);
        line.setSelectedIndex(0);
        rightPanel.add(lineType);
        rightPanel.add(line);
    }
    public  void getFontType(JPanel rightPanel,Component component){
        JLabel fontType = new JLabel("字体类型");
        fontType.setBounds(100,120,50,20);
        String[] listType = new String[]{"宋体","黑体"};
        final JComboBox<String> myFont= new JComboBox<>(listType);
        myFont.setEditable(true);
        myFont.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=myFont.getSelectedIndex();
                    String selectedItem = (String)myFont.getSelectedItem();
//                    RightPanel.this.component.setFont(new Font(selectedItem,
//                            RightPanel.this.component.getFont().getStyle(),
//                            RightPanel.this.component.getFont().getSize()));
                }
            }
        });
        myFont.setBounds(180,120,50,20);
        myFont.setSelectedIndex(0);
        rightPanel.add(fontType);
        rightPanel.add(myFont);
    }
    public  void getFontSize(JPanel rightPanel,Component component){
        JLabel fontSize = new JLabel("字体大小");
        fontSize.setBounds(100,160,50,20);
        Integer [] listFont = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
        final JComboBox<Integer> myfont= new JComboBox<>(listFont);
        myfont.setEditable(true);
        myfont.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=myfont.getSelectedIndex();
                    Integer selectedItem = (Integer) myfont.getSelectedItem();
                    int item=selectedItem.intValue();
//                    RightPanel.this.component.setFont(new Font(RightPanel.this.component.getFont().getName(),
//                            RightPanel.this.component.getFont().getStyle(),
//                            item));
                }
            }
        });
        myfont.setBounds(180,160,50,20);
        myfont.setSelectedIndex(5);
        rightPanel.add(fontSize);
        rightPanel.add(myfont);
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
            getFontSize(rightPanel,this.component);
            getFontType(rightPanel,this.component);
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
            getLineBold(rightPanel,this.component);
            getLineT(rightPanel,this.component);
        }else{
            rightPanel.removeAll();
        }
        return rightPanel;
    }
}




