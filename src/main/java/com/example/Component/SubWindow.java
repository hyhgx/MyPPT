package com.example.Component;
import com.example.graphics.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

import static com.example.Component.MyFrame.sortComponent;

public class SubWindow extends JFrame{
    private  MyJList list1;
    private  CanvasPanels panelxx;
    public SubWindow(MyJList jlist, CanvasPanels panels){
        list1=jlist;
        panelxx=panels;
        this.setAlwaysOnTop(true);
        this.setResizable ( false );
        this.setTitle("按类型输出");
        this.setVisible(true);//从获取屏幕大小
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width ;
        int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
        int winWidth=600;
        int winHeight=300;
        int winx =( screenWidth - winWidth )/2; //
        int winY =( screenHeight- winHeight )/2;
        this.setBounds( winx +50, winY +50, winWidth , winHeight );

        JPanel panelx = new JPanel(new BorderLayout());
        this.add(panelx);
        DefaultMutableTreeNode rootNode=new DefaultMutableTreeNode("类型");
        DefaultMutableTreeNode T=new DefaultMutableTreeNode("文本框");
        DefaultMutableTreeNode R=new DefaultMutableTreeNode("直角矩形");
        DefaultMutableTreeNode L=new DefaultMutableTreeNode("直线");
        DefaultMutableTreeNode RO=new DefaultMutableTreeNode("圆角矩形");
        DefaultMutableTreeNode A=new DefaultMutableTreeNode("箭头");
        DefaultMutableTreeNode C=new DefaultMutableTreeNode("圆");
        int f1=0;
        int f2=0;
        int f3=0;
        int f4=0;
        int f5=0;
        int f6=0;
        JTree tree=new JTree(rootNode);
        tree.setShowsRootHandles(true);
        tree.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tree);
        panelx.add(scrollPane, BorderLayout.CENTER);
        this.setContentPane(panelx);
        this.setVisible(true);
        for (Component x:sortComponent)
        {
            if(x instanceof MyText)
            {
                if(f1==0)
                {
                    rootNode.add(T);
                    f1=1;
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    T.add(Node);
                }
                else {
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    T.add(Node);
                }
            }
            if(x instanceof MyRect)
            {
                if(f2==0)
                {
                    rootNode.add(R);
                    f2=1;
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    R.add(Node);
                }
                else {
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    R.add(Node);
                }
            }
            if(x instanceof MyLine)
            {
                if(f3==0)
                {
                    rootNode.add(L);
                    f3=1;
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    L.add(Node);
                }
                else {
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    L.add(Node);
                }
            }
            if(x instanceof MyRoundRect)
            {
                if(f4==0)
                {
                    rootNode.add(RO);
                    f4=1;
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    RO.add(Node);
                }
                else {
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    RO.add(Node);
                }
            }
            if(x instanceof MyArrowHead)
            {
                if(f5==0)
                {
                    rootNode.add(A);
                    f5=1;
                    DefaultMutableTreeNode Node=new DefaultMutableTreeNode(x.getName());
                    A.add(Node);
                }
                else {
                DefaultMutableTreeNode Node=new DefaultMutableTreeNode(x.getName());
                A.add(Node);
                }
            }
            if(x instanceof MyCircle)
            {
                if(f6==0)
                {
                    rootNode.add(C);
                    f6=1;
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    C.add(Node);
                }
                else {
                    DefaultMutableTreeNode Node = new DefaultMutableTreeNode(x.getName());
                    C.add(Node);
                }
            }
        }
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                Object lastPathComponent = e.getPath().getLastPathComponent();
                if(lastPathComponent instanceof DefaultMutableTreeNode ){
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) lastPathComponent;
                    if(node.getLevel()==2){
                        TreeNode parent = node.getParent();
                        int index = parent.getIndex(node);
                        DefaultMutableTreeNode x=(DefaultMutableTreeNode)parent;
                        String str=(String) x.getUserObject();
                        int i,m;
                        for(i=0;i<sortComponent.size();i++)
                        {
                            if(str.equals("文本框"))
                            {
                                if(sortComponent.get(i) instanceof MyText)
                                {
                                    if(index==0)
                                        break;
                                    else index--;
                                }
                            }
                            if(str.equals("直角矩形"))
                            {
                                if(sortComponent.get(i) instanceof MyRect)
                                {
                                    if(index==0) break;
                                    else index--;
                                }
                            }
                            if(str.equals("直线"))
                            {
                                if(sortComponent.get(i) instanceof MyLine)
                                {
                                    if(index==0)
                                        break;
                                    else index--;
                                }
                            }
                            if(str.equals("圆角矩形"))
                            {
                                if(sortComponent.get(i) instanceof MyRoundRect)
                                {
                                    if(index==0)
                                        break;
                                    else index--;
                                }
                            }
                            if(str.equals("箭头"))
                            {
                                if(sortComponent.get(i) instanceof MyArrowHead)
                                {
                                    if(index==0)
                                        break;
                                    else index--;
                                }
                            }
                            if(str.equals("圆"))
                            {
                                if(sortComponent.get(i) instanceof MyCircle)
                                {
                                    if(index==0)
                                        break;
                                    else index--;
                                }
                            }
                        }
                        java.util.List<CanvasPanel> searchPanels=new ArrayList<CanvasPanel>();
                        searchPanels=panels.returnPanels();
                        m=i;
                        int j;
                        int flag=0;
                        for(j=0;j<searchPanels.size();j++)
                        {
                            CanvasPanel searchpanel=new CanvasPanel();
                            searchpanel=searchPanels.get(j);
                            Component[] searchcomponent = searchpanel.getComponents();
                            for (Component ss : searchcomponent) {
                                if (ss instanceof MyComponent) {
                                    if(i==0)
                                    {
                                        flag=1;
                                        break;
                                    }
                                    else i--;
                                }
                            }
                            if(i==0&&flag==1) break;
                        }
                        int pa=jlist.getCurrentPage();
                        if(pa!=j)
                        jlist.setCurrentPanel(j);
                        sortComponent.get(m).getFocus();
                    }
                }

            }
        });

    }

}