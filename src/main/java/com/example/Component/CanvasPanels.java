package com.example.Component;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class CanvasPanels extends Panel {

    private MyFrame frame=null;
    private final List<CanvasPanel> panels=new LinkedList<CanvasPanel>();

    private CanvasPanel currentPanel;

    public CanvasPanels(MyFrame frame){
        this.setLayout(null);
        this.frame=frame;
    }

    public void addPanel(){
        panels.add(new CanvasPanel(frame));
    }
    public void deletePanel(int index){
        panels.remove(index);
    }
    public CanvasPanel getPanel(int index){
        return panels.get(index);
    }
    public List<CanvasPanel> returnPanels(){return panels; }
    public void changeCurrentPanel(CanvasPanel newPanel){
        if(currentPanel!=null){
            this.remove(currentPanel);
        }
        if(newPanel!=null){
            this.add(newPanel);
        }
        currentPanel=newPanel;
        this.repaint();
    }
    public void setCurrentPanel(int index){
        currentPanel=panels.get(index);
        this.add(currentPanel);
    }
    public int getPanelsSize(){
        return panels.size();
    }

}
