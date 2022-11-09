package com.example;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class MyFrame extends JFrame {

    private Boolean toolBarVisible = false;

    private CanvasPanels panels=new CanvasPanels();
    private MyJList jlist=new MyJList(panels,this);

    private CanvasPanel centerPanel = jlist.getCurrentPanel();


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
        JButton button = new JButton("画笔");
        JButton button1 = new JButton("长方形");
        JButton button2 = new JButton("添加文字");
        JButton button3 = new JButton("橡皮檫");
        JButton button4 = new JButton("椭圆");
        JButton button5 = new JButton("直线");
        final JToolBar jToolBar = new JToolBar();
        jToolBar.setFloatable(false);
        jToolBar.add(button);
        jToolBar.add(button1);
        jToolBar.add(button2);
        jToolBar.add(button3);
        jToolBar.add(button4);
        jToolBar.add(button5);
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
        Panel rightPanel = new Panel();

        //设置布局
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(jScrollPane, BorderLayout.WEST);
        panel.add(jToolBar, BorderLayout.NORTH);
        this.add(panel);
        this.setVisible(true);

    }

    public void changeCenterPanel(){
        centerPanel=jlist.getCurrentPanel();
        centerPanel.repaint();
    }

}
