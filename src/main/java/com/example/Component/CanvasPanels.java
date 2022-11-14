package com.example.Component;

import java.util.LinkedList;
import java.util.List;

public class CanvasPanels {
    private List<CanvasPanel> panels=new LinkedList<CanvasPanel>();
    public void addPanel(){
        panels.add(new CanvasPanel());
    }
    public void deletePanel(int index){
        panels.remove(index);
    }
    public CanvasPanel getPanel(int index){
        return panels.get(index);
    }

    public int getIndex(CanvasPanel p){
        return panels.indexOf(p);
    }


}
