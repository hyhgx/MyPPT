package com.example.Component;

import com.example.graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RightPanel extends JPanel{
    private static final long serialVersionUID = -6352788025440244338L;
    private Image image = new ImageIcon("src/main/resources/images/img.png").getImage();
    public RightPanel(){
        this.setPreferredSize(new Dimension(300, 800));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    public void getComponentName(Component component){
        JLabel label = new JLabel("名称：");
        label.setBounds(100,240,60,20);
        JTextField name = new JTextField(component.getName());
        name.setBounds(180,240,60,20);
        name.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                component.setName(name.getText());
            }
        });
        this.add(label);
        this.add(name);
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
        line.setEditable(false);
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
        line.setEditable(false);
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
    public  void getFontType( final Component component){
        JLabel fontType = new JLabel("字体类型");
        final MyText myText=(MyText) component;
        fontType.setBounds(100,120,50,20);
        String[] listType = new String[]{"普通","宋体","黑体","粗体","斜体"};
        String[] listType1 = new String[]{"Dialog.plain","宋体","黑体","Dialog.bold","Dialog.italic"};
        int i=0;
        for(i=0;i<listType1.length;i++){
            if(listType1[i].equals(myText.font.getFontName())){
                break;
            }
        }
        final JComboBox<String> myFont= new JComboBox<>(listType);
        myFont.setEditable(false);
        myFont.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    int index=myFont.getSelectedIndex();
                    String selectedItem = (String)myFont.getSelectedItem();
                    if(selectedItem.equals("普通")){
                        selectedItem="Dialog.plain";
                        myText.font=new Font(selectedItem, Font.PLAIN, myText.font.getSize());
                    }else if(selectedItem.equals("粗体")){
                        selectedItem="Dialog.bold";
                        myText.font=new Font(selectedItem, Font.BOLD, myText.font.getSize());
                    }else if(selectedItem.equals("斜体")){
                        selectedItem="Dialog.italic";
                        myText.font=new Font(selectedItem, Font.ITALIC, myText.font.getSize());
                    }else{
                        myText.font=new Font(selectedItem,  myText.font.getStyle(), myText.font.getSize());
                    }
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
        myfont.setEditable(false);
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
        this.removeAll();
        this.setLayout(null);
        if (type.equals("文本框")) {
            MyText myText=(MyText) component;
            getFontSize(myText);
            getFontType(myText);
            getComponentName(myText);
        } else if (type.equals("直线")) {
            MyLine myLine=(MyLine) component;
            JButton color = getBorderColor(type,myLine);//线条颜色
            this.add(color);
            getLineBold(type, myLine);//粗细
            getLineT(type, myLine);//类型，实线虚线
            getComponentName(myLine);
        } else if (type.equals("直角矩形")) {
            MyRect myRect=(MyRect) component;
            JButton color = getFillColor(type,myRect);
            this.add(color);
            JButton color1 = getBorderColor(type,myRect);
            this.add(color1);
            getComponentName(myRect);
        }else if (type.equals("圆角矩形")) {
            MyRoundRect myRoundRect=(MyRoundRect) component;
            JButton color = getFillColor(type,myRoundRect);
            this.add(color);
            JButton color1 = getBorderColor(type,myRoundRect);
            this.add(color1);
            getComponentName(myRoundRect);
        }else if (type.equals("椭圆")) {
            MyCircle myCircle=(MyCircle) component;
            JButton color = getFillColor(type,myCircle);
            this.add(color);
            JButton color1 = getBorderColor(type,myCircle);
            this.add(color1);
            getComponentName(myCircle);
        }else if (type.equals("箭头")) {
            MyArrowHead myArrowHead = (MyArrowHead) component;
            JButton color = getFillColor(type, myArrowHead);
            this.add(color);
            JButton color1 = getBorderColor(type, myArrowHead);
            this.add(color1);
            getComponentName(myArrowHead);
        } else {
            this.removeAll();
        }
        this.repaint();
    }
}




