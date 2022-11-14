package com.example.Component;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class MyFrame extends JFrame {

    private Boolean toolBarVisible = false;

    private final CanvasPanels panels=new CanvasPanels(this);
    private final MyJList jlist=new MyJList(panels,this);



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
        JMenuItem jMenuItemSave = new JMenuItem("保存");
        JMenuItem jMenuItemNew = new JMenuItem("新建");
        JMenuItem jMenuItemOpen = new JMenuItem("打开");
        JMenuItem jMenuItemSaveE = new JMenuItem("另存为");
        JMenuItem jMenuItemBack = new JMenuItem("退出");
        fileMenu.add(jMenuItemNew);
        fileMenu.addSeparator();
        fileMenu.add(jMenuItemOpen);
        fileMenu.addSeparator();
        fileMenu.add(jMenuItemSave);
        fileMenu.addSeparator();
        fileMenu.add(jMenuItemSaveE);
        fileMenu.addSeparator();
        fileMenu.add(jMenuItemBack);
        jMenuBar.add(fileMenu);
        jMenuBar.add(toolMenu);
        this.setJMenuBar(jMenuBar);


        //设置工具栏
        JButton button = new JButton();//画笔
        JButton button1 = new JButton();//长方形
        JButton button2 = new JButton();//文字
        JButton button3 = new JButton();//橡皮檫
        JButton button4 = new JButton();//椭圆
        JButton button5 = new JButton();//直线

        button.setBounds(0,0,40,40);
        button3.setBounds(40,0,20,20);
        button2.setBounds(40,20,20,20);
        button1.setBounds(60,0,20,20);
        button4.setBounds(60,20,20,20);
        button5.setBounds(80,0,20,20);


        String path="src/main/resources/images/huabi.png";
        ImageIcon icon =new ImageIcon(path);
        Image temp=icon.getImage().getScaledInstance(button.getWidth(),button.getHeight(),icon.getImage().SCALE_AREA_AVERAGING);
        icon=new ImageIcon(temp);
        button.setIcon(icon);

        String path1="src/main/resources/images/changfangxing.png";
        ImageIcon icon1 =new ImageIcon(path1);
        Image temp1=icon1.getImage().getScaledInstance(button1.getWidth(),button1.getHeight(),icon1.getImage().SCALE_AREA_AVERAGING);
        icon1=new ImageIcon(temp1);
        button1.setIcon(icon1);

        String path2="src/main/resources/images/wenzi.png";
        ImageIcon icon2 =new ImageIcon(path2);
        Image temp2=icon2.getImage().getScaledInstance(button2.getWidth(),button2.getHeight(),icon2.getImage().SCALE_AREA_AVERAGING);
        icon2=new ImageIcon(temp2);
        button2.setIcon(icon2);

        String path3="src/main/resources/images/xiangpica.png";
        ImageIcon icon3 =new ImageIcon(path3);
        Image temp3=icon3.getImage().getScaledInstance(button3.getWidth(),button3.getHeight(),icon3.getImage().SCALE_AREA_AVERAGING);
        icon3=new ImageIcon(temp3);
        button3.setIcon(icon3);

        String path4="src/main/resources/images/tuoyuan.png";
        ImageIcon icon4 =new ImageIcon(path4);
        Image temp4=icon4.getImage().getScaledInstance(button4.getWidth(),button4.getHeight(),icon4.getImage().SCALE_AREA_AVERAGING);
        icon4=new ImageIcon(temp4);
        button4.setIcon(icon4);

        String path5="src/main/resources/images/zhixian.png";
        ImageIcon icon5 =new ImageIcon(path5);
        Image temp5=icon5.getImage().getScaledInstance(button5.getWidth(),button5.getHeight(),icon5.getImage().SCALE_AREA_AVERAGING);
        icon5=new ImageIcon(temp5);
        button5.setIcon(icon5);

        final JToolBar jToolBar = new JToolBar();
        jToolBar.setLayout(null);
        jToolBar.setPreferredSize(new Dimension(700,40));
        jToolBar.setFloatable(false);
        jToolBar.add(button);
        jToolBar.add(button1);
        jToolBar.add(button2);
        jToolBar.add(button3);
        jToolBar.add(button4);
        jToolBar.add(button5);
        jToolBar.setBackground(new Color(255,255,255));
        jToolBar.setVisible(false);


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

        //设置左侧

        JScrollPane jScrollPane = new JScrollPane(jlist, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //设置右侧
        RightPanel rightPanel1 = new RightPanel("文字");
        JPanel rightPanel = rightPanel1.returnPanel();

        //设置布局
        panel.add(panels, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(jScrollPane, BorderLayout.WEST);
        panel.add(jToolBar, BorderLayout.NORTH);
        this.add(panel);
        this.setVisible(true);

    }


}
