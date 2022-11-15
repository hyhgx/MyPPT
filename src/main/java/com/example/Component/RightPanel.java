package com.example.Component;

import com.example.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RightPanel extends BackgroundPanel{
    private String panelType;
    private Component component;
    public RightPanel(){
    }
    public RightPanel(Component component,String type){
        this.component=component;
        this.panelType=type;
    }
    public JButton getColorButton(String type,Component component){
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
    public  JButton getFillColor(final String t, final Component component){
        JButton color = new JButton("颜色填充");
        color.setBounds(100,80,100,20);
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t.equals("直角矩形")){
                    MyRect myRect=(MyRect) component;
                    Color color1=JColorChooser.showDialog(null,"颜色选择",Color.red);
                    if(color1!=null){
                        myRect.fillColor=color1;
                        myRect.repaint();
                    }
                }else if(t.equals("圆角矩形")){
                    MyRoundRect myRoundRect=(MyRoundRect) component;
                    Color color1=JColorChooser.showDialog(null,"颜色选择",Color.red);
                    if(color1!=null){
                        myRoundRect.fillColor=color1;
                        myRoundRect.repaint();
                    }
                }else if(t.equals("椭圆")){
                    MyCircle myCircle=(MyCircle) component;
                    Color color1=JColorChooser.showDialog(null,"颜色选择",Color.red);
                    if(color1!=null){
                        myCircle.fillColor=color1;
                        myCircle.repaint();
                    }
                }else if(t.equals("箭头")){
                    MyArrowHead myArrowHead=(MyArrowHead) component;
                    Color color1=JColorChooser.showDialog(null,"颜色选择",Color.red);
                    if(color1!=null){
                        myArrowHead.fillColor=color1;
                        myArrowHead.repaint();
                    }
                }

            }
        });
        return color;
    }
    public  JButton getBorderColor(final String t, final Component component){
        final JButton color = new JButton("线条颜色");
        color.setBounds(100,120,100,20);
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t.equals("直角矩形")){
                    MyRect myRect=(MyRect) component;
                    Color color1=JColorChooser.showDialog(null,"颜色选择",Color.red);
                    if(color1!=null){
                        myRect.lineColor=color1;
                        myRect.repaint();
                    }
                }else if(t.equals("圆角矩形")){
                    MyRoundRect myRoundRect=(MyRoundRect) component;
                    Color color1=JColorChooser.showDialog(null,"颜色选择",Color.red);
                    if(color1!=null){
                        myRoundRect.lineColor=color1;
                        myRoundRect.repaint();
                    }
                }else if(t.equals("椭圆")){
                    MyCircle myCircle=(MyCircle) component;
                    Color color1=JColorChooser.showDialog(null,"颜色选择",Color.red);
                    if(color1!=null){
                        myCircle.lineColor=color1;
                        myCircle.repaint();
                    }
                }else if(t.equals("箭头")){
                    MyArrowHead myArrowHead=(MyArrowHead) component;
                    Color color1=JColorChooser.showDialog(null,"颜色选择",Color.red);
                    if(color1!=null){
                        myArrowHead.lineColor=color1;
                        myArrowHead.repaint();
                    }
                }else if(t.equals("直线")){
                    MyLine myLine=(MyLine) component;
                    Color color1=JColorChooser.showDialog(null,"颜色选择",Color.red);
                    if(color1!=null){
                        myLine.lineColor=color1;
                        myLine.repaint();
                    }
                }

            }
        });
        return color;
    }
    public  void getLineT(final String t, final Component component){
        JLabel lineType = new JLabel("线条类型");
        final MyLine myLine=(MyLine) component;
        lineType.setBounds(100,160,50,20);
        String[] listLine = new String[]{"实线","虚线"};
        int i=0;
        if(myLine.lineT){
            i=1;
        }
        final JComboBox<String> line= new JComboBox<>(listLine);
        line.setEditable(true);
        line.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=line.getSelectedIndex();
                    Object selectedItem = line.getSelectedItem();
                    String t1=(String) selectedItem;
                    if(t.equals("直线")){
                        if(t1.equals("虚线")){
                            myLine.lineT=true;//代表画虚线
                            myLine.repaint();
                        }else{
                            myLine.lineT=false;
                            myLine.repaint();
                        }
                    }
                }
            }
        });
        line.setBounds(180,160,50,20);
        line.setSelectedIndex(i);
        this.add(lineType);
        this.add(line);
    }
    public  void getLineBold(final String t, final Component component){
        JLabel lineType = new JLabel("线条粗细");
        final MyLine myLine=(MyLine) component;
        lineType.setBounds(100,200,50,20);
        String[] listLine = new String[]{"粗线","正常"};
        int i=0;
        if(myLine.lineWidth==1.0f){
            i=1;
        }
        final JComboBox<String> line= new JComboBox<>(listLine);
        line.setEditable(true);
        line.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=line.getSelectedIndex();
                    Object selectedItem = line.getSelectedItem();
                    String t1=(String) selectedItem;
                    if(t.equals("直线")){
                        if(t1.equals("粗线")){
                            myLine.lineWidth=2.0f;
                            myLine.repaint();
                        }else{
                            myLine.lineWidth=1.0f;
                            myLine.repaint();
                        }
                    }
                }
            }
        });
        line.setBounds(180,200,50,20);
        line.setSelectedIndex(i);
        this.add(lineType);
        this.add(line);
    }
    public  void fontBold(final Component component){
        JLabel lineType = new JLabel("线条粗细");
        final MyText myText=(MyText) component;
        lineType.setBounds(100,200,50,20);
        String[] listLine = new String[]{"普通","粗体","斜体"};
        int i;
        if(myText.font.getStyle()==Font.PLAIN){
            i=0;
        }else if(myText.font.getStyle()==Font.BOLD){
            i=1;
        }else{
            i=2;
        }
        final JComboBox<String> line= new JComboBox<>(listLine);
        line.setSelectedIndex(i);
        line.setEditable(true);
        line.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=line.getSelectedIndex();
                    Object selectedItem = line.getSelectedItem();
                    String t1=(String) selectedItem;
                    if(t1.equals("斜体")){
                        myText.font=new Font(myText.getFont().getFontName(), Font.ITALIC, myText.getFont().getSize());
                    }else if(t1.equals("粗体")){
                        myText.font=new Font(myText.getFont().getFontName(), Font.BOLD, myText.getFont().getSize());
                    }else{
                        myText.font=new Font(myText.getFont().getFontName(), Font.PLAIN, myText.getFont().getSize());
                    }
                    myText.repaint();
                }
            }
        });
        line.setBounds(180,200,50,20);
        this.add(lineType);
        this.add(line);
    }
    public  void getFontType( final Component component){
        JLabel fontType = new JLabel("字体类型");
        final MyText myText=(MyText) component;
        fontType.setBounds(100,120,50,20);
        String[] listType = new String[]{"Dialog.plain","宋体","黑体"};
        int i=0;
        for(i=0;i<listType.length;i++){
            if(listType[i].equals(myText.font.getFontName())){
                break;
            }
        }
        final JComboBox<String> myFont= new JComboBox<>(listType);
        myFont.setEditable(true);
        myFont.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=myFont.getSelectedIndex();
                    String selectedItem = (String)myFont.getSelectedItem();
                    myText.font=new Font(selectedItem, myText.font.getStyle(), myText.font.getSize());
                    myText.repaint();
                }
            }
        });
        myFont.setBounds(180,120,50,20);
        myFont.setSelectedIndex(i);
        this.add(fontType);
        this.add(myFont);
    }
    public  void getFontSize( final Component component){
        JLabel fontSize = new JLabel("字体大小");
        final MyText myText=(MyText) component;
        fontSize.setBounds(100,160,50,20);
        Integer [] listFont = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13
        ,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        final JComboBox<Integer> myfont= new JComboBox<>(listFont);
        myfont.setEditable(true);
        myfont.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=myfont.getSelectedIndex();
                    Integer selectedItem = (Integer) myfont.getSelectedItem();
                    int item=selectedItem.intValue();
                    myText.font=new Font(myText.text.getFont().getFontName(), myText.text.getFont().getStyle(),item);
                    myText.repaint();
                }
            }
        });
        myfont.setBounds(180,160,50,20);
        myfont.setSelectedIndex(myText.font.getSize()-1);
        this.add(fontSize);
        this.add(myfont);
    }

    public void returnPanel(String type,JComponent component) {

        this.setLayout(null);
        this.setPreferredSize(new Dimension(300, 800));
        this.removeAll();
        this.repaint();
        if (type.equals("文本框")) {
            MyText myText=(MyText) component;
            fontBold(myText);
            getFontSize( myText);
            getFontType( myText);
        } else if (type.equals("直线")) {
            MyLine myLine=(MyLine) component;
            JButton color = getBorderColor("直线",myLine);//线条颜色
            this.add(color);
            getLineBold("直线", myLine);//粗细
            getLineT("直线", myLine);//类型，实线虚线
        } else if (type.equals("直角矩形")) {
            MyRect myRect=(MyRect) component;
            JButton color = getFillColor("直角矩形",myRect);
            this.add(color);
            JButton color1 = getBorderColor("直角矩形",myRect);
            this.add(color1);
        }else if (type.equals("圆角矩形")) {
            MyRoundRect myRect=(MyRoundRect) component;
            JButton color = getFillColor("圆角矩形",myRect);
            this.add(color);
            JButton color1 = getBorderColor("圆角矩形",myRect);
            this.add(color1);
        }else if (type.equals("椭圆")) {
            MyCircle myCircle=(MyCircle) component;
            JButton color = getFillColor("椭圆",myCircle);
            this.add(color);
            JButton color1 = getBorderColor("椭圆",myCircle);
            this.add(color1);
        }else if (type.equals("箭头")) {
            MyArrowHead myArrowHead = (MyArrowHead) component;
            this.removeAll();
            JButton color = getFillColor("箭头", myArrowHead);
            this.add(color);
            JButton color1 = getBorderColor("箭头", myArrowHead);
            this.add(color1);
        }
           else {
            this.removeAll();
        }

    }
}




