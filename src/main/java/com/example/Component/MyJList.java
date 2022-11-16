package com.example.Component;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MyJList extends JList<String> {
    private final DefaultListModel<String> model = new DefaultListModel<>();//左侧列表的内容
    private int currentPage = 0;//从0开始
    private final List<BufferedImage> images = new ArrayList<>();
    private CanvasPanels panels = null;

    private final MyFrame frame;

    public MyJList(CanvasPanels p, MyFrame f) {
        this.setPanels(p);
        this.frame = f;
        init();
    }

    private void init() {
        //设置左侧PPT列表内容
        this.setModel(model);
        addPage();//添加一页
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置只能单选
        this.setCellRenderer(new ListCellRenderer<String>() {
            private final JLabel jLabel = new JLabel();
            final Border lineBorder = BorderFactory.createMatteBorder(5, 5, 0, 0, new Color(183, 71, 42));
            final Border emptyBorder = BorderFactory.createMatteBorder(4, 4, 0, 0, new Color(191, 191, 191));

            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {

                jLabel.setText(value);
                jLabel.setHorizontalAlignment(JLabel.LEFT);
                jLabel.setVerticalAlignment(JLabel.TOP);
                jLabel.setHorizontalTextPosition(JLabel.LEFT);
                jLabel.setVerticalTextPosition(JLabel.TOP);
                jLabel.setPreferredSize(new Dimension(250, 200));
                jLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                jLabel.setOpaque(true);

                jLabel.setIcon(new ImageIcon(images.get(index)));
                jLabel.setIconTextGap(20);

                if (isSelected) {
                    jLabel.setForeground(new Color(183, 71, 42));
                    jLabel.setBackground(new Color(255, 255, 255));
                    jLabel.setBorder(lineBorder);
                } else {
                    jLabel.setForeground(list.getForeground());
                    jLabel.setBackground(new Color(255, 255, 255));
                    jLabel.setBorder(emptyBorder);
                }
                return jLabel;
            }
        });//设置列表显示样式
        final JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItemAddPage = new JMenuItem("新增");
        JMenuItem jMenuItemDeletePage = new JMenuItem("删除");
        jPopupMenu.add(jMenuItemAddPage);
        jPopupMenu.add(jMenuItemDeletePage);
        this.add(jPopupMenu);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int i = MyJList.this.locationToIndex(e.getPoint());
                if (e.getButton() == 3) {//右键
                    if (i != currentPage) {
                        MyJList.this.setSelectedIndex(i);//设置右键也能选中元素
                        currentPage = i;
                        panels.changeCurrentPanel(getCurrentPanel());
                    }
                    jPopupMenu.show(MyJList.this, e.getX(), e.getY());
                } else if (e.getButton() == 1) {//左键
                    if (i != currentPage) {
                        currentPage = i;
                        panels.changeCurrentPanel(getCurrentPanel());
                    }

                }

            }
        });
        jMenuItemAddPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPage();
            }
        });
        jMenuItemDeletePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //当前一个也没有时直接返回
                if (currentPage == -1) {
                    return;
                }
                int selectedIndex = MyJList.this.getSelectedIndex();
                if (currentPage == panels.getPanelsSize() - 1) {//删除最后一个时
                    if (currentPage == 0) {//前面没有了
                        panels.changeCurrentPanel(null);
                        currentPage = -1;
                        deletePage(selectedIndex);
                    } else {//前面还有
                        panels.changeCurrentPanel(panels.getPanel(currentPage--));
                        deletePage(selectedIndex);
                        MyJList.this.setSelectedIndex(currentPage);
                    }
                } else {//不是最后一个时
                    panels.changeCurrentPanel(panels.getPanel(currentPage + 1));
                    deletePage(selectedIndex);
                    MyJList.this.setSelectedIndex(currentPage);//currentPage不变
                }
            }
        });
    }

    private void addPage() {
        boolean isEmpty = panels.getPanelsSize() == 0;//如果从零变成1,自动选中
        if (isEmpty) {
            currentPage = 0;//要先把currentPage变成0,再添加model
        }
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(new Color(255,255,255));
        graphics.fillRect(0,0,bufferedImage.getWidth(),bufferedImage.getHeight());
        images.add(bufferedImage);
        panels.addPanel();
        model.addElement(String.valueOf(panels.getPanelsSize()));
        if (isEmpty) {//选中要在添加model后
            this.setSelectedIndex(0);
            panels.changeCurrentPanel(getCurrentPanel());
        }
    }

    private void deletePage(int selectedIndex) {
        for (int i = selectedIndex + 1; i < model.size(); ++i) {
            model.setElementAt(String.valueOf(i), i);//重新设置编号
        }
        model.remove(selectedIndex);
        images.remove(selectedIndex);
        panels.deletePanel(selectedIndex);

    }

    public void setPanels(CanvasPanels p) {
        panels = p;
    }

    public CanvasPanel getCurrentPanel() {
        return panels.getPanel(currentPage);
    }

    public void updateImage(BufferedImage img) {
        Image tmp = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        this.images.set(currentPage,newImage);
        this.repaint();
    }

}
