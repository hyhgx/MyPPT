package com.example;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class MyFrame extends JFrame {
    private static final int DEFAULT_WIDTH=1200;
    private  static  final  int DEFAULT_HEIGHT=800;

    private DefaultListModel<String> model=new DefaultListModel<>();//左侧列表的内容
    public MyFrame(){
        init();
    }
    private void init (){
        this.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MyPPT");
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
        toolMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                jToolBar.setVisible(true);
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
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });





        //设置左侧列表
        for(int i=0;i<100;++i){
            model.addElement(String.valueOf(i));
        }
        JList<String> jlist=new JList<>(model);
        JScrollPane jScrollPane = new JScrollPane(jlist);
        jlist.setFixedCellHeight(250);
        jlist.setFixedCellWidth(250);
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jlist.setCellRenderer(renderer);

        //设置中间
        Panel centerPanel = new Panel();
        //设置右侧
        RightPanel rightPanel1 = new RightPanel("文字");
        Panel rightPanel = rightPanel1.returnPanel();


        panel.add(centerPanel,BorderLayout.CENTER);
        panel.add(rightPanel,BorderLayout.EAST);
        panel.add(jScrollPane,BorderLayout.WEST);
        panel.add(jToolBar, BorderLayout.NORTH);
        //rightPanel.setSize(100,800);
        this.add(panel);
        this.setVisible(true);
    }
}
