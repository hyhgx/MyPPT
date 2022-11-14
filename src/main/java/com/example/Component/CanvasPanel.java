package com.example.Component;

import com.example.graphics.*;

import java.awt.*;

public  class CanvasPanel extends Panel {
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
