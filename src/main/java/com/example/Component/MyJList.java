package com.example.Component;

import com.example.graphics.CanvasPanels;

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
    private DefaultListModel<String> model = new DefaultListModel<>();//左侧列表的内容
    private int pageCount = 3;
    private int currentPage = 0;//从0开始
    private List<BufferedImage> images = new ArrayList<>();
    private CanvasPanels panels = null;

    private final MyFrame frame;

    public MyJList(CanvasPanels p, MyFrame f) {
        this.setPanels(p);
        this.frame = f;
        init();
    }

    private void init() {
        //设置左侧PPT列表内容
        for (int i = 1; i <= pageCount; ++i) {
            model.addElement(String.valueOf(i));
            //图片
            final BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(new Color(239, 99, 12));
            graphics.drawRect(0, 0, 200, 200);
            graphics.drawLine(0, 0, 200, 200);
            images.add(bufferedImage);
            panels.addPanel();
        }
        this.setModel(model);

        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.setSelectedIndex(0);//默认选第0个
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
        });
        final JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItemAddPage = new JMenuItem("新增");
        JMenuItem jMenuItemDeletePage = new JMenuItem("删除");
        jPopupMenu.add(jMenuItemAddPage);
        jPopupMenu.add(jMenuItemDeletePage);
        this.add(jPopupMenu);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {//右键
                    MyJList.this.setSelectedIndex(MyJList.this.locationToIndex(e.getPoint()));//设置右键也能选中元素
                    jPopupMenu.show(MyJList.this, e.getX(), e.getY());
                } else if (e.getButton() == 1) {//左键
                    currentPage = MyJList.this.getSelectedIndex();
                    MyJList.this.frame.changeCenterPanel();
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
                int selectedIndex = MyJList.this.getSelectedIndex();
                deletePage(selectedIndex);
            }
        });
    }

    private void addPage() {
        model.addElement(String.valueOf(++pageCount));
        images.add(new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB));
        panels.addPanel();
    }

    private void deletePage(int selectedIndex) {
        for (int i = selectedIndex + 1; i < model.size(); ++i) {
            model.setElementAt(String.valueOf(i), i);//重新设置编号
        }
        model.remove(selectedIndex);
        images.remove(selectedIndex);
        panels.deletePanel(selectedIndex);
        pageCount--;
    }

    public void setPanels(CanvasPanels p) {
        panels = p;
    }

    public CanvasPanels.CanvasPanel getCurrentPanel() {
        return panels.getPanel(currentPage);
    }
}
