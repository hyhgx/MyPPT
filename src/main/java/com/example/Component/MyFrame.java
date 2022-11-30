package com.example.Component;

import com.example.graphics.*;
import org.apache.maven.model.Resource;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyFrame extends JFrame {
    public RightPanel rightPanel = new RightPanel();
    private Boolean toolBarVisible = false;
    public String type = "正常";
    public File oldFile;
    public boolean isNew = true;
    public final CanvasPanels panels = new CanvasPanels(this);
    private final MyJList jlist = new MyJList(panels, this);
    public final MyOuter out = new MyOuter();
    private String module;
    private String sort;
    public List<List<MyComponent>> searchComponent = new ArrayList<>();
    public static List<MyComponent> sortComponent = new ArrayList<>();
    private int num;
    private int page;

    public MyFrame() {
        init();
    }

    private void init() {
        //初始化
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocation(0, 0);
        this.setTitle("MyPPT");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //设置菜单栏
        JPanel panel = new JPanel(new BorderLayout());
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setBackground(new Color(255, 255, 255));
        JMenu fileMenu = new JMenu("文件");
        JMenu toolMenu = new JMenu("工具");
        JMenu view = new JMenu("播放");
        JMenu help=new JMenu("帮助");
        JMenuItem fromBegin = new JMenuItem("从头开始");
        JMenuItem fromNow = new JMenuItem("从当前页面开始");
        JMenuItem jMenuItemSave = new JMenuItem("保存");
        JMenuItem jMenuItemNew = new JMenuItem("新建");
        JMenuItem jMenuItemOpen = new JMenuItem("打开");
        JMenuItem jMenuItemSaveE = new JMenuItem("另存为");
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                MyFrame.this.panels.getCurrentPanel().focusChanged();
                MyFrame.this.panels.getCurrentPanel().requestFocus(true);
                MyFrame.this.panels.getCurrentPanel().updateLeftImage();
            }
        });
        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                new HelpPanel();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int op = JOptionPane.showConfirmDialog(MyFrame.this, "是否保存");
                if (op == JOptionPane.YES_OPTION) {
                    if (doFile() == JFileChooser.APPROVE_OPTION) {
                        System.exit(0);
                    }
                } else if (op == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        jMenuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int op = JOptionPane.showConfirmDialog(MyFrame.this, "是否保存");
                if (op == JOptionPane.YES_OPTION) {
                    if (doFile() == JFileChooser.APPROVE_OPTION) {
                        MyFrame.this.jlist.deleteAll();
                        MyFrame.this.repaint();
                        MyFrame.this.isNew = true;
                        MyFrame.this.oldFile = null;
                    }
                } else if (op == JOptionPane.NO_OPTION) {
                    MyFrame.this.jlist.deleteAll();
                    MyFrame.this.repaint();
                    MyFrame.this.isNew = true;
                    MyFrame.this.oldFile = null;
                }
            }
        });
        jMenuItemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    int i = chooser.showOpenDialog(null);
                    if (i == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        MyFile myFile = new MyFile(MyFrame.this.panels.returnPanels(), file);
                        myFile.getPanels();
                        setJlist(myFile.jsonList);
                        MyFrame.this.isNew = false;
                        MyFrame.this.oldFile = file;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        jMenuItemSaveE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int option = chooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    String name = chooser.getName(file);
                    if (!name.contains(".myppt")) {
                        file = new File(chooser.getCurrentDirectory(), name + ".myppt");
                    }
                    MyFile myFile = new MyFile(MyFrame.this.panels.returnPanels(), file);
                    try {
                        myFile.getJsonData();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        jMenuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doFile();
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
        view.add(fromBegin);
        view.add(fromNow);
        jMenuBar.add(fileMenu);
        jMenuBar.add(toolMenu);
        jMenuBar.add(view);
        jMenuBar.add(help);
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

        JButton button8 = new JButton();//图片 !!!!新增
        JButton button9 = new JButton();
        button9.setBounds(350, 0, 40, 40);
        URL resource9 =this.getClass().getClassLoader().getResource("images/piliang.png");
        String path9 = "src/main/resources/images/piliang.png";
        ImageIcon icon9 = new ImageIcon(resource9);
        Image temp9 = icon9.getImage().getScaledInstance(button9.getWidth(), button9.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon9 = new ImageIcon(temp9);
        button9.setIcon(icon9);
        button9.setToolTipText("批量操作");

        JButton search1 = new JButton();
        search1.setBounds(600, 0, 20, 20);
        URL resourcese =this.getClass().getClassLoader().getResource("images/search.png");
        String searchs = "src/main/resources/images/search.png";
        ImageIcon icons = new ImageIcon(resourcese);
        Image temps = icons.getImage().getScaledInstance(search1.getWidth(), search1.getHeight(), Image.SCALE_AREA_AVERAGING);
        icons = new ImageIcon(temps);
        search1.setIcon(icons);

        JButton search2 = new JButton();
        search2.setBounds(600, 20, 20, 20);
        search2.setVisible(true);
        search2.setIcon(icons);

        search1.setToolTipText("精确搜索");
        search2.setToolTipText("模糊搜索");

        button.setBounds(0, 0, 40, 40);
        button3.setBounds(40, 0, 20, 20);
        button2.setBounds(40, 20, 20, 20);
        button1.setBounds(60, 0, 20, 20);
        button4.setBounds(60, 20, 20, 20);
        button5.setBounds(80, 0, 20, 20);
        button6.setBounds(80, 20, 20, 20);
        button7.setBounds(100, 0, 40, 40);
        button8.setBounds(400, 0, 40, 40);

        JButton sb = new JButton();
        sb.setBounds(150, 0, 40, 40);
        sb.setVisible(true);
        URL resourcesb =this.getClass().getClassLoader().getResource("images/sb.png");
        ImageIcon iconsb = new ImageIcon(resourcesb);
        Image tempsb = iconsb.getImage().getScaledInstance(sb.getWidth(), sb.getHeight(), Image.SCALE_AREA_AVERAGING);
        iconsb = new ImageIcon(tempsb);
        sb.setIcon(iconsb);
        sb.setToolTipText("鼠标");
        sb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = "普通";
                MyFrame.this.setCursor(null);
            }
        });
        URL resource8 =this.getClass().getClassLoader().getResource("images/tupian.png");
        ImageIcon icon8 = new ImageIcon(resource8);

        Image temp8 = icon8.getImage().getScaledInstance(button8.getWidth(), button8.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon8 = new ImageIcon(temp8);
        button8.setIcon(icon8);

        String path = "src/main/resources/images/huabi.png";
        URL resource =this.getClass().getClassLoader().getResource("images/huabi.png");
        ImageIcon icon = new ImageIcon(resource);
        Image temp = icon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon = new ImageIcon(temp);
        button.setIcon(icon);

        String path1 = "src/main/resources/images/changfangxing.png";
        URL resource1 =this.getClass().getClassLoader().getResource("images/changfangxing.png");
        ImageIcon icon1 = new ImageIcon(resource1);
        Image temp1 = icon1.getImage().getScaledInstance(button1.getWidth(), button1.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon1 = new ImageIcon(temp1);
        button1.setIcon(icon1);

        String path2 = "src/main/resources/images/wenzi.png";
        URL resource2 =this.getClass().getClassLoader().getResource("images/wenzi.png");
        ImageIcon icon2 = new ImageIcon(resource2);
        Image temp2 = icon2.getImage().getScaledInstance(button2.getWidth(), button2.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon2 = new ImageIcon(temp2);
        button2.setIcon(icon2);

        String path3 = "src/main/resources/images/xiangpica.png";
        URL resource3 =this.getClass().getClassLoader().getResource("images/xiangpica.png");
        ImageIcon icon3 = new ImageIcon(resource3);
        Image temp3 = icon3.getImage().getScaledInstance(button3.getWidth(), button3.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon3 = new ImageIcon(temp3);
        button3.setIcon(icon3);

        String path4 = "src/main/resources/images/tuoyuan.png";
        URL resource4 =this.getClass().getClassLoader().getResource("images/tuoyuan.png");
        ImageIcon icon4 = new ImageIcon(resource4);
        Image temp4 = icon4.getImage().getScaledInstance(button4.getWidth(), button4.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon4 = new ImageIcon(temp4);
        button4.setIcon(icon4);

        String path5 = "src/main/resources/images/zhixian.png";
        URL resource5 =this.getClass().getClassLoader().getResource("images/zhixian.png");
        ImageIcon icon5 = new ImageIcon(resource5);
        Image temp5 = icon5.getImage().getScaledInstance(button5.getWidth(), button5.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon5 = new ImageIcon(temp5);
        button5.setIcon(icon5);

        String path6 = "src/main/resources/images/yuanjiaojuxing.png";
        URL resource6 =this.getClass().getClassLoader().getResource("images/yuanjiaojuxing.png");
        ImageIcon icon6 = new ImageIcon(resource6);
        Image temp6 = icon6.getImage().getScaledInstance(button6.getWidth(), button6.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon6 = new ImageIcon(temp6);
        button6.setIcon(icon6);

        String path7 = "src/main/resources/images/jiantou.png";
        URL resource7 =this.getClass().getClassLoader().getResource("images/jiantou.png");
        ImageIcon icon7 = new ImageIcon(resource7);
        Image temp7 = icon7.getImage().getScaledInstance(button7.getWidth(), button7.getHeight(), Image.SCALE_AREA_AVERAGING);
        icon7 = new ImageIcon(temp7);
        button7.setIcon(icon7);


        out.setBounds(200, 0, 50, 50);

        JButton color1 = new JButton();
        color1.setBackground(new Color(0, 0, 0));
        color1.setBounds(260, 0, 15, 15);
        color1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(0, 0, 0);
            }
        });
        JButton color2 = new JButton();
        color2.setBackground(new Color(255, 255, 255));
        color2.setBounds(275, 0, 15, 15);
        color2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(255, 255, 255);
            }
        });
        JButton color3 = new JButton();
        color3.setBackground(new Color(192, 192, 192));
        color3.setBounds(290, 0, 15, 15);
        color3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(192, 192, 192);
            }
        });
        JButton color4 = new JButton();
        color4.setBackground(new Color(255, 0, 0));
        color4.setBounds(260, 15, 15, 15);
        color4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(255, 0, 0);
            }
        });
        JButton color5 = new JButton();
        color5.setBackground(new Color(255, 255, 0));
        color5.setBounds(275, 15, 15, 15);
        color5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(255, 255, 0);
            }
        });
        JButton color6 = new JButton();
        color6.setBackground(new Color(0, 0, 255));
        color6.setBounds(290, 15, 15, 15);
        color6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(0, 0, 255);
            }
        });
        JButton color7 = new JButton();
        color7.setBackground(new Color(0, 255, 0));
        color7.setBounds(260, 30, 15, 15);
        color7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(0, 255, 0);
            }
        });
        JButton color8 = new JButton();
        color8.setBackground(new Color(128, 42, 42));
        color8.setBounds(275, 30, 15, 15);
        color8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.changeColor(128, 42, 42);
            }
        });
        JButton color9 = new JButton("...");
        color9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorx = JColorChooser.showDialog(null, "颜色选择", Color.red);
                out.changeColor1(colorx);
            }
        });
        color9.setBounds(290, 30, 15, 15);

        JButton last = new JButton("上");
        JButton next = new JButton("下");
        JButton cancel = new JButton("退");
        cancel.setBounds(640, 12, 30, 20);
        cancel.setVisible(false);
        last.setBounds(600, 0, 30, 20);
        last.setVisible(false);
        next.setBounds(600, 25, 30, 20);
        next.setVisible(false);
        JButton sorting = new JButton();
        sorting.setBounds(680, 0, 40, 40);
        sorting.setVisible(true);
        String sorting1 = "src/main/resources/images/sort.png";
        URL resources1 =this.getClass().getClassLoader().getResource("images/sort.png");
        ImageIcon iconso = new ImageIcon(resources1);
        Image tempso = iconso.getImage().getScaledInstance(sorting.getWidth(), sorting.getHeight(), Image.SCALE_AREA_AVERAGING);
        iconso = new ImageIcon(tempso);
        sorting.setIcon(iconso);

        JTextField text = new JTextField();
        text.setBounds(450, 8, 150, 30);
        text.setColumns(10);
        text.setFont(new Font("黑体", Font.PLAIN, 20));//设置字体格式
        search1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<CanvasPanel> searchPanels = new ArrayList<CanvasPanel>();
                searchPanels = panels.returnPanels();
                if (searchPanels.size() >= 1) {
                    module = text.getText();
                    last.setVisible(true);
                    next.setVisible(true);
                    cancel.setVisible(true);
                    search1.setVisible(false);
                    search2.setVisible(false);
                    searchComponent.clear();
                    num = 0;
                    page = 0;
                    for (int i = 0; i < searchPanels.size(); i++) {
                        List<MyComponent> second = new ArrayList<MyComponent>();
                        CanvasPanel searchpanel = searchPanels.get(i);
                        Component[] searchcomponent = searchpanel.getComponents();
                        for (Component x : searchcomponent) {
                            if (x instanceof MyComponent && x.getName().equals(module)) {
                                second.add((MyComponent) x);
                            }
                        }
                        searchComponent.add(second);
                    }
                    for (int i = 0; i < searchComponent.size(); i++) {
                        if (searchComponent.get(i).size() == 0) {
                            page++;
                            if ((page == searchComponent.size() - 1) && searchComponent.get(page).size() == 0) {
                                search1.setVisible(true);
                                search2.setVisible(true);
                                last.setVisible(false);
                                next.setVisible(false);
                                cancel.setVisible(false);
                                searchComponent.clear();
                            } else if (page > searchComponent.size() - 1) {
                                search1.setVisible(true);
                                search2.setVisible(true);
                                last.setVisible(false);
                                next.setVisible(false);
                                cancel.setVisible(false);
                                searchComponent.clear();
                            }
                        } else {
                            jlist.setCurrentPanel(page);
                            searchComponent.get(page).get(num).getFocus();
                            break;
                        }
                    }
                }
            }
        });
        search2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<CanvasPanel> searchPanels1 = new ArrayList<CanvasPanel>();
                searchPanels1 = panels.returnPanels();
                module = text.getText();
                if (!module.equals("")) {
                    if (searchPanels1.size() >= 1) {
                        last.setVisible(true);
                        next.setVisible(true);
                        cancel.setVisible(true);
                        search1.setVisible(false);
                        search2.setVisible(false);
                        searchComponent.clear();
                        num = 0;
                        page = 0;
                        for (int i = 0; i < searchPanels1.size(); i++) {
                            List<MyComponent> second1 = new ArrayList<MyComponent>();
                            CanvasPanel searchpanel1 = searchPanels1.get(i);
                            Component[] searchcomponent = searchpanel1.getComponents();
                            for (Component x : searchcomponent) {
                                if (x instanceof MyComponent && x.getName().contains(module)) {
                                    second1.add((MyComponent) x);
                                }
                            }
                            searchComponent.add(second1);
                        }
                        for (int i = 0; i < searchComponent.size(); i++) {
                            if (searchComponent.get(i).size() == 0) {
                                page++;
                                if ((page == searchComponent.size() - 1) && searchComponent.get(page).size() == 0) {
                                    search1.setVisible(true);
                                    search2.setVisible(true);
                                    last.setVisible(false);
                                    next.setVisible(false);
                                    cancel.setVisible(false);
                                    searchComponent.clear();
                                } else if (page > searchComponent.size() - 1) {
                                    search1.setVisible(true);
                                    search2.setVisible(true);
                                    last.setVisible(false);
                                    next.setVisible(false);
                                    cancel.setVisible(false);
                                    searchComponent.clear();
                                }
                            } else {
                                jlist.setCurrentPanel(page);
                                searchComponent.get(page).get(num).getFocus();
                                break;
                            }
                        }
                    }
                }
            }
        });
        last.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (num >= 1) {
                        num--;
                        searchComponent.get(page).get(num).getFocus();
                    } else if (num == 0 && page >= 1) {
                        page--;
                        while (searchComponent.get(page).size() == 0 && page >= 1) {
                            page--;
                        }
                        if (page != 0 || searchComponent.get(0).size() != 0) {
                            jlist.setCurrentPanel(page);
                            num = searchComponent.get(page).size() - 1;
                            searchComponent.get(page).get(num).getFocus();
                        }
                    }
                } catch (Exception exception) {
                    return;
                }
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if ((num == searchComponent.get(page).size() - 1) && (page < searchComponent.size() - 1)) {
                        page++;
                        while (searchComponent.get(page).size() == 0 && (page < searchComponent.size() - 1)) {
                            page++;
                        }
                        if ((page != searchComponent.size() - 1) || searchComponent.get(page).size() != 0) {
                            num = 0;
                            jlist.setCurrentPanel(page);
                            searchComponent.get(page).get(num).getFocus();
                        }
                    } else if (num < searchComponent.get(page).size() - 1) {
                        num++;
                        searchComponent.get(page).get(num).getFocus();
                    }
                } catch (Exception exception) {
                    return;
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search1.setVisible(true);
                search2.setVisible(true);
                last.setVisible(false);
                next.setVisible(false);
                cancel.setVisible(false);
                searchComponent.clear();
            }
        });
        sorting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort = text.getText();
                sortComponent.clear();
                List<CanvasPanel> searchPanels;
                searchPanels = panels.returnPanels();
                for (int i = 0; i < searchPanels.size(); i++) {
                    CanvasPanel searchpanel = searchPanels.get(i);
                    Component[] searchcomponent = searchpanel.getComponents();
                    for (Component x : searchcomponent) {
                        if (x instanceof MyText || x instanceof MyRect || x instanceof MyLine || x instanceof MyRoundRect || x instanceof MyArrowHead || x instanceof MyCircle) {
                            sortComponent.add((MyComponent) x);
                        }
                    }
                }
                new SubWindow(MyFrame.this.jlist, MyFrame.this.panels);
            }
        });

        final JToolBar jToolBar = new JToolBar();
        jToolBar.setLayout(null);
        jToolBar.setPreferredSize(new Dimension(700, 45));
        jToolBar.setFloatable(false);
        jToolBar.add(last);
        jToolBar.add(next);
        jToolBar.add(sorting);
        jToolBar.add(sb);
        jToolBar.add(cancel);
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
        jToolBar.add(search1);
        jToolBar.add(text);
        jToolBar.add(search2);
        jToolBar.setBackground(new Color(255, 255, 255));
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
        sorting.setToolTipText("分类查找");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = "画笔";
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = "直角矩形";
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = "文本框";
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = "橡皮擦";
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = "椭圆";
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = "直线";
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = "圆角矩形";
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = "箭头";
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
                type = "矩形选区";
                MyFrame.this.setCursor(null);
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
                if (MyFrame.this.panels.returnPanels().size() != 0) {
                    MyFrame.this.setVisible(false);
                    ViewFrame viewFrame = new ViewFrame(0, MyFrame.this);
                    viewFrame.repaint();
                }
            }
        });
        fromNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MyFrame.this.panels.returnPanels().size() != 0) {
                    MyFrame.this.setVisible(false);
                    ViewFrame viewFrame = new ViewFrame(MyFrame.this.jlist.returnIndex(), MyFrame.this);
                    viewFrame.repaint();
                }
            }
        });
        //设置左侧

        JScrollPane jScrollPane = new JScrollPane(jlist, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rightPanel.add(jScrollPane);
        //设置右侧

        //设置布局
        panel.add(panels, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(jScrollPane, BorderLayout.WEST);
        panel.add(jToolBar, BorderLayout.NORTH);
        this.add(panel);
        this.setVisible(true);

    }

    public MyJList getJlist() {
        return jlist;
    }

    public int doFile() {
        int option = JFileChooser.APPROVE_OPTION;
        try {
            if (!isNew) {
                MyFile myFile = new MyFile(MyFrame.this.panels.returnPanels(), MyFrame.this.oldFile);
                myFile.getJsonData();
            } else {
                JFileChooser chooser = new JFileChooser();
                option = chooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    String name = chooser.getName(file);
                    if (!name.contains(".myppt")) {
                        file = new File(chooser.getCurrentDirectory(), name + ".myppt");
                    }
                    MyFile myFile = new MyFile(MyFrame.this.panels.returnPanels(), file);
                    myFile.getJsonData();
                    MyFrame.this.oldFile = file;
                    MyFrame.this.isNew = false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return option;
    }

    public void setJlist(JsonList jsonList) throws Exception {
        ArrayList<CanvasPanel> list = new ArrayList<>();
        for (int i = 0; i < jsonList.jsonDataList.size(); i++) {
            CanvasPanel p1 = new CanvasPanel(MyFrame.this);
            for (int j = 0; j < jsonList.jsonDataList.get(i).myRects.size(); j++) {
                MyRect m = new MyRect(jsonList.jsonDataList.get(i).myRects.get(j).minX,
                        jsonList.jsonDataList.get(i).myRects.get(j).minY,
                        jsonList.jsonDataList.get(i).myRects.get(j).maxX,
                        jsonList.jsonDataList.get(i).myRects.get(j).maxY,
                        jsonList.jsonDataList.get(i).myRects.get(j).componentName,
                        new Color(jsonList.jsonDataList.get(i).myRects.get(j).lineBorderRed,
                                jsonList.jsonDataList.get(i).myRects.get(j).lineBorderGreen,
                                jsonList.jsonDataList.get(i).myRects.get(j).lineBorderBlue,
                                jsonList.jsonDataList.get(i).myRects.get(j).lineBorderAlpha),
                        new Color(jsonList.jsonDataList.get(i).myRects.get(j).fillRed, jsonList.jsonDataList.get(i).myRects.get(j).fillGreen,
                                jsonList.jsonDataList.get(i).myRects.get(j).fillBlue, jsonList.jsonDataList.get(i).myRects.get(j).fillAlpha));
                p1.addListener(m);
                p1.add(m);
            }
            for (int j = 0; j < jsonList.jsonDataList.get(i).myRoundRects.size(); j++) {
                MyRoundRect m = new MyRoundRect(jsonList.jsonDataList.get(i).myRoundRects.get(j).minX,
                        jsonList.jsonDataList.get(i).myRoundRects.get(j).minY,
                        jsonList.jsonDataList.get(i).myRoundRects.get(j).maxX,
                        jsonList.jsonDataList.get(i).myRoundRects.get(j).maxY,
                        jsonList.jsonDataList.get(i).myRoundRects.get(j).componentName,
                        new Color(jsonList.jsonDataList.get(i).myRoundRects.get(j).lineBorderRed,
                                jsonList.jsonDataList.get(i).myRoundRects.get(j).lineBorderGreen,
                                jsonList.jsonDataList.get(i).myRoundRects.get(j).lineBorderBlue,
                                jsonList.jsonDataList.get(i).myRoundRects.get(j).lineBorderAlpha),
                        new Color(jsonList.jsonDataList.get(i).myRoundRects.get(j).fillRed, jsonList.jsonDataList.get(i).myRoundRects.get(j).fillGreen,
                                jsonList.jsonDataList.get(i).myRoundRects.get(j).fillBlue, jsonList.jsonDataList.get(i).myRoundRects.get(j).fillAlpha));
                p1.addListener(m);
                p1.add(m);
            }
            for (int j = 0; j < jsonList.jsonDataList.get(i).myCircles.size(); j++) {
                MyCircle m = new MyCircle(jsonList.jsonDataList.get(i).myCircles.get(j).minX,
                        jsonList.jsonDataList.get(i).myCircles.get(j).minY,
                        jsonList.jsonDataList.get(i).myCircles.get(j).maxX,
                        jsonList.jsonDataList.get(i).myCircles.get(j).maxY,
                        jsonList.jsonDataList.get(i).myCircles.get(j).componentName,
                        new Color(jsonList.jsonDataList.get(i).myCircles.get(j).lineBorderRed,
                                jsonList.jsonDataList.get(i).myCircles.get(j).lineBorderGreen,
                                jsonList.jsonDataList.get(i).myCircles.get(j).lineBorderBlue,
                                jsonList.jsonDataList.get(i).myCircles.get(j).lineBorderAlpha),
                        new Color(jsonList.jsonDataList.get(i).myCircles.get(j).fillRed, jsonList.jsonDataList.get(i).myCircles.get(j).fillGreen,
                                jsonList.jsonDataList.get(i).myCircles.get(j).fillBlue, jsonList.jsonDataList.get(i).myCircles.get(j).fillAlpha));
                p1.addListener(m);
                p1.add(m);
            }
            for (int j = 0; j < jsonList.jsonDataList.get(i).myArrowHeads.size(); j++) {
                MyArrowHead m = new MyArrowHead(jsonList.jsonDataList.get(i).myArrowHeads.get(j).minX,
                        jsonList.jsonDataList.get(i).myArrowHeads.get(j).minY,
                        jsonList.jsonDataList.get(i).myArrowHeads.get(j).maxX,
                        jsonList.jsonDataList.get(i).myArrowHeads.get(j).maxY,
                        jsonList.jsonDataList.get(i).myArrowHeads.get(j).componentName,
                        new Color(jsonList.jsonDataList.get(i).myArrowHeads.get(j).lineBorderRed,
                                jsonList.jsonDataList.get(i).myArrowHeads.get(j).lineBorderGreen,
                                jsonList.jsonDataList.get(i).myArrowHeads.get(j).lineBorderBlue,
                                jsonList.jsonDataList.get(i).myArrowHeads.get(j).lineBorderAlpha),
                        new Color(jsonList.jsonDataList.get(i).myArrowHeads.get(j).fillRed, jsonList.jsonDataList.get(i).myArrowHeads.get(j).fillGreen,
                                jsonList.jsonDataList.get(i).myArrowHeads.get(j).fillBlue, jsonList.jsonDataList.get(i).myArrowHeads.get(j).fillAlpha));
                p1.addListener(m);
                p1.add(m);
            }
            for (int j = 0; j < jsonList.jsonDataList.get(i).myTexts.size(); j++) {
                MyText m = new MyText(jsonList.jsonDataList.get(i).myTexts.get(j).minX,
                        jsonList.jsonDataList.get(i).myTexts.get(j).minY,
                        jsonList.jsonDataList.get(i).myTexts.get(j).maxX,
                        jsonList.jsonDataList.get(i).myTexts.get(j).maxY,
                        jsonList.jsonDataList.get(i).myTexts.get(j).componentName,
                        new Color(jsonList.jsonDataList.get(i).myTexts.get(j).red,
                                jsonList.jsonDataList.get(i).myTexts.get(j).green,
                                jsonList.jsonDataList.get(i).myTexts.get(j).blue,
                                jsonList.jsonDataList.get(i).myTexts.get(j).alpha),
                        new Font(jsonList.jsonDataList.get(i).myTexts.get(j).fontName,
                                jsonList.jsonDataList.get(i).myTexts.get(j).fontStyle,
                                jsonList.jsonDataList.get(i).myTexts.get(j).fontSize),
                        jsonList.jsonDataList.get(i).myTexts.get(j).contentText
                );
                p1.addListener(m);
                p1.add(m);
            }
            for (int j = 0; j < jsonList.jsonDataList.get(i).myLines.size(); j++) {
                MyLine m = new MyLine(jsonList.jsonDataList.get(i).myLines.get(j).x1,
                        jsonList.jsonDataList.get(i).myLines.get(j).x2,
                        jsonList.jsonDataList.get(i).myLines.get(j).y1,
                        jsonList.jsonDataList.get(i).myLines.get(j).y2,
                        jsonList.jsonDataList.get(i).myLines.get(j).componentName,
                        new Color(jsonList.jsonDataList.get(i).myLines.get(j).lineRed,
                                jsonList.jsonDataList.get(i).myLines.get(j).lineGreen,
                                jsonList.jsonDataList.get(i).myLines.get(j).lineBlue,
                                jsonList.jsonDataList.get(i).myLines.get(j).lineAlpha),
                        jsonList.jsonDataList.get(i).myLines.get(j).lineT,
                        jsonList.jsonDataList.get(i).myLines.get(j).lineWidth
                );
                p1.addListener(m);
                p1.add(m);
            }
            //点
            LinkedList<LinkedList<Point>> lines = jsonList.jsonDataList.get(i).mypointsBeans.lines;
            LinkedList<Color> colors = new LinkedList<>();
            for (int j = 0; j < jsonList.jsonDataList.get(i).mypointsBeans.red.size(); j++) {
                colors.add(new Color(jsonList.jsonDataList.get(i).mypointsBeans.red.get(j),
                        jsonList.jsonDataList.get(i).mypointsBeans.green.get(j),
                        jsonList.jsonDataList.get(i).mypointsBeans.blue.get(j),
                        jsonList.jsonDataList.get(i).mypointsBeans.alpha.get(j)));
            }
            p1.points.setLines(lines);
            p1.points.setColors(colors);
            for (int j = 0; j < jsonList.jsonDataList.get(i).myImages.size(); j++) {
                MyImage myImage = new MyImage(jsonList.jsonDataList.get(i).myImages.get(j).minX,
                        jsonList.jsonDataList.get(i).myImages.get(j).minY,
                        jsonList.jsonDataList.get(i).myImages.get(j).maxX,
                        jsonList.jsonDataList.get(i).myImages.get(j).maxY,
                        jsonList.jsonDataList.get(i).myImages.get(j).s);
                p1.addListener(myImage);
                p1.add(myImage);
            }
            list.add(p1);
        }
        jlist.load(list);
    }

    public void showImageFileOpenDialog(Component parent) {
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
                if (currentPanel != null) {
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
