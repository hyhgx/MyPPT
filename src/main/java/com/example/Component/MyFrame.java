package com.example.Component;

import com.example.graphics.MyImage;

import javax.imageio.ImageIO;
import com.example.graphics.MyOuter;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {
    public RightPanel rightPanel = new RightPanel();
    private Boolean toolBarVisible = false;
    public String type="正常";
    public final CanvasPanels panels=new CanvasPanels(this);
    private final MyJList jlist=new MyJList(panels,this);
    public  final  MyOuter out=new MyOuter();
    public MyFrame() {
        init();
    }

    private void init() {
        //初始化
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MyPPT");

        //设置菜单栏
        JPanel panel = new JPanel(new BorderLayout());
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setBackground(new Color(255,255,255));
        JMenu fileMenu = new JMenu("文件");
        JMenu toolMenu = new JMenu("工具");
        JMenu view = new JMenu("播放");
        JMenuItem fromBegin = new JMenuItem("从头开始");
        JMenuItem fromNow = new JMenuItem("从当前页面开始");
        JMenuItem jMenuItemSave = new JMenuItem("保存");
        JMenuItem jMenuItemNew = new JMenuItem("新建");
        JMenuItem jMenuItemOpen = new JMenuItem("打开");
        JMenuItem jMenuItemSaveE = new JMenuItem("另存为");
        JMenuItem jMenuItemBack = new JMenuItem("退出");
        jMenuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MyFile myFile = new MyFile(MyFrame.this.panels.returnPanels());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        fileMenu.add(jMenuItemNew);
        fileMenu.addSeparator();
        fileMenu.add(jMenuItemOpen);
        fileMenu.addSeparator();
        fileMenu.add(jMenuItemSave);
        fileMenu.addSeparator();
        fileMenu.add(jMenuItemSaveE);
        fileMenu.addSeparator();
        fileMenu.add(jMenuItemBack);
        view.add(fromBegin);
        view.add(fromNow);
        jMenuBar.add(fileMenu);
        jMenuBar.add(toolMenu);
        jMenuBar.add(view);
        this.setJMenuBar(jMenuBar);

        //设置工具栏
        JButton button = new JButton();//画笔
        JButton button1 = new JButton();//长方形
        JButton button2 = new JButton();//文字
        JButton button3 = new JButton();//橡皮檫
        JButton button4 = new JButton();//椭圆
        JButton button5 = new JButton();//直线
        JButton button6 = new JButton();//圆角矩形
        JButton button7 = new JButton();//箭头

        JButton button8=new JButton();//图片 !!!!新增
        JButton button9=new JButton("矩形选取");

        button.setBounds(0,0,40,40);
        button3.setBounds(40,0,20,20);
        button2.setBounds(40,20,20,20);
        button1.setBounds(60,0,20,20);
        button4.setBounds(60,20,20,20);
        button5.setBounds(80,0,20,20);
        button6.setBounds(80,20,20,20);
        button7.setBounds(100,0,40,40);
        button8.setBounds(150,0,40,40);
        button9.setBounds(350,0,40,40);

        String path8="src/main/resources/images/tupian.png";
        ImageIcon icon8 =new ImageIcon(path8);
        Image temp8=icon8.getImage().getScaledInstance(button8.getWidth(),button8.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon8=new ImageIcon(temp8);
        button8.setIcon(icon8);

        String path="src/main/resources/images/huabi.png";
        ImageIcon icon =new ImageIcon(path);
        Image temp=icon.getImage().getScaledInstance(button.getWidth(),button.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon=new ImageIcon(temp);
        button.setIcon(icon);

        String path1="src/main/resources/images/changfangxing.png";
        ImageIcon icon1 =new ImageIcon(path1);
        Image temp1=icon1.getImage().getScaledInstance(button1.getWidth(),button1.getHeight(),Image.SCALE_AREA_AVERAGING);
        icon1=new ImageIcon(temp1);
        button1.setIcon(icon1);

        String path2="src/main/resources/images/wenzi.png";
        ImageIcon icon2 =new ImageIcon(path2);
        Image temp2=icon2.getImage().getScaledInstance(button2.getWidth(),button2.getHeight(),Image.SCALE_AREA_AVERAGING);
        icon2=new ImageIcon(temp2);
        button2.setIcon(icon2);

        String path3="src/main/resources/images/xiangpica.png";
        ImageIcon icon3 =new ImageIcon(path3);
        Image temp3=icon3.getImage().getScaledInstance(button3.getWidth(),button3.getHeight(),Image.SCALE_AREA_AVERAGING);
        icon3=new ImageIcon(temp3);
        button3.setIcon(icon3);

        String path4="src/main/resources/images/tuoyuan.png";
        ImageIcon icon4 =new ImageIcon(path4);
        Image temp4=icon4.getImage().getScaledInstance(button4.getWidth(),button4.getHeight(),Image.SCALE_AREA_AVERAGING);
        icon4=new ImageIcon(temp4);
        button4.setIcon(icon4);

        String path5="src/main/resources/images/zhixian.png";
        ImageIcon icon5 =new ImageIcon(path5);
        Image temp5=icon5.getImage().getScaledInstance(button5.getWidth(),button5.getHeight(),Image.SCALE_AREA_AVERAGING);
        icon5=new ImageIcon(temp5);
        button5.setIcon(icon5);

        String path6="src/main/resources/images/yuanjiaojuxing.png";
        ImageIcon icon6 =new ImageIcon(path6);
        Image temp6=icon6.getImage().getScaledInstance(button6.getWidth(),button6.getHeight(),Image.SCALE_AREA_AVERAGING);
        icon6=new ImageIcon(temp6);
        button6.setIcon(icon6);

        String path7="src/main/resources/images/jiantou.png";
        ImageIcon icon7 =new ImageIcon(path7);
        Image temp7=icon7.getImage().getScaledInstance(button7.getWidth(),button7.getHeight(),Image.SCALE_AREA_AVERAGING);
        icon7=new ImageIcon(temp7);
        button7.setIcon(icon7);


        out.setBounds(200,0,50,50);

        JButton color1=new JButton();
        color1.setBackground(new Color(0,0,0));
        color1.setBounds(260,0,15,15);
        color1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(0,0,0);
            }
        });
        JButton color2=new JButton();
        color2.setBackground(new Color(255,255,255));
        color2.setBounds(275,0,15,15);
        color2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(255,255,255);
            }
        });
        JButton color3=new JButton();
        color3.setBackground(new Color(192,192,192));
        color3.setBounds(290,0,15,15);
        color3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(192,192,192);
            }
        });
        JButton color4=new JButton();
        color4.setBackground(new Color(255,0,0));
        color4.setBounds(260,15,15,15);
        color4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(255,0,0);
            }
        });
        JButton color5=new JButton();
        color5.setBackground(new Color(255,255,0));
        color5.setBounds(275,15,15,15);
        color5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(255,255,0);
            }
        });
        JButton color6=new JButton();
        color6.setBackground(new Color(0,0,255));
        color6.setBounds(290,15,15,15);
        color6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(0,0,255);
            }
        });
        JButton color7=new JButton();
        color7.setBackground(new Color(0,255,0));
        color7.setBounds(260,30,15,15);
        color7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(0,255,0);
            }
        });
        JButton color8=new JButton();
        color8.setBackground(new Color(128,42,42));
        color8.setBounds(275,30,15,15);
        color8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(128,42,42);
            }
        });
        JButton color9=new JButton("...");
        color9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorx=JColorChooser.showDialog(null,"颜色选择",Color.red);
                out.changeColor1(colorx);
            }
        });
        color9.setBounds(290,30,15,15);


        final JToolBar jToolBar = new JToolBar();
        jToolBar.setLayout(null);
        jToolBar.setPreferredSize(new Dimension(700,45));
        jToolBar.setFloatable(false);
        jToolBar.add(button);
        jToolBar.add(button1);
        jToolBar.add(button2);
        jToolBar.add(button3);
        jToolBar.add(button4);
        jToolBar.add(button5);
        jToolBar.add(button6);
        jToolBar.add(button7);
        jToolBar.add(button8);
        jToolBar.add(button9);
        jToolBar.add(out);
        jToolBar.add(color1);
        jToolBar.add(color2);
        jToolBar.add(color3);
        jToolBar.add(color4);
        jToolBar.add(color5);
        jToolBar.add(color6);
        jToolBar.add(color7);
        jToolBar.add(color8);
        jToolBar.add(color9);
        jToolBar.setBackground(new Color(255,255,255));
        jToolBar.setVisible(false);

        button.setToolTipText("画笔");
        button1.setToolTipText("直角矩形");
        button2.setToolTipText("文本框");
        button3.setToolTipText("橡皮擦");
        button4.setToolTipText("椭圆");
        button5.setToolTipText("直线");
        button6.setToolTipText("圆角矩形");
        button7.setToolTipText("箭头");
        button8.setToolTipText("图片");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="画笔";
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="直角矩形";
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="文本框";

            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="橡皮擦";
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="椭圆";
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="直线";
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="圆角矩形";
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="箭头";
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showImageFileOpenDialog(MyFrame.this);
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="矩形选区";
            }
        });


        //绑定菜单栏事件

        toolMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                toolBarVisible = !toolBarVisible;
                jToolBar.setVisible(toolBarVisible);

            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        fileMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                jToolBar.setVisible(false);
                toolBarVisible = false;
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        view.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                jToolBar.setVisible(false);
                toolBarVisible = false;
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
//设置播放
        fromBegin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MyFrame.this.panels.returnPanels().size()!=0){
                    MyFrame.this.setVisible(false);
                    ViewFrame viewFrame = new ViewFrame( 0,MyFrame.this);
                    viewFrame.repaint();
                }
            }
        });
        fromNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MyFrame.this.panels.returnPanels().size()!=0){MyFrame.this.setVisible(false);
                    ViewFrame viewFrame = new ViewFrame( MyFrame.this.jlist.returnIndex(),MyFrame.this);
                    viewFrame.repaint();}
            }
        });
        //设置左侧

        JScrollPane jScrollPane = new JScrollPane(jlist, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //设置右侧

        //设置布局
        panel.add(panels, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(jScrollPane, BorderLayout.WEST);
        panel.add(jToolBar, BorderLayout.NORTH);
        this.add(panel);
        this.setVisible(true);

    }
    public MyJList getJlist(){
        return jlist;
    }

    public void showImageFileOpenDialog(Component parent){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // 设置是否允许多选
        fileChooser.setMultiSelectionEnabled(false);
        // 设置默认使用的文件过滤器
        fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png)", "jpg", "png"));
        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"确定", 则获取选择的文件路径
            File file = fileChooser.getSelectedFile();
            try {
                Image image = ImageIO.read(file);
                CanvasPanel currentPanel = this.jlist.getCurrentPanel();
                if(currentPanel!=null){
                    MyImage myImage = new MyImage(50, 50, image.getWidth(null) + 50, image.getHeight(null) + 50, image);
                    currentPanel.addListener(myImage);
                    currentPanel.add(myImage);
                    currentPanel.focusChanged();
                    currentPanel.requestFocus(true);
                    currentPanel.repaint();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
