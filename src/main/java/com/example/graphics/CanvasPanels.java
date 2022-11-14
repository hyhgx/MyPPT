package com.example.graphics;

import com.example.Component.MyArrowHead;
import com.example.graphics.MyCircle;
import com.example.graphics.MyComponent;
import com.example.graphics.MyRoundRect;
import com.example.graphics.MyText;

import java.awt.*;
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

    public static class CanvasPanel extends Panel {
        public void focusChanged(){
            Component[] components = this.getComponents();
            for(Component i: components){
                if(i instanceof MyComponent){
                    MyComponent j=(MyComponent) i;
                    j.lostFocus();
                }
                if(i instanceof MyCircle){
                    MyCircle k=(MyCircle) i;
                    k.lostFocus();
                }
                if(i instanceof MyRoundRect){
                    MyRoundRect q=(MyRoundRect) i;
                    q.lostFocus();
                }
                if(i instanceof MyArrowHead){
                    MyArrowHead p =(MyArrowHead) i;
                    p.lostFocus();
                }
                if(i instanceof MyText){
                    MyText p =(MyText) i;
                    p.lostFocus();
                }

            }
        }


        public CanvasPanel(){
            this.setBackground(new Color(255,255,255));
        }
    }
}
