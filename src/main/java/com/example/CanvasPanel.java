package com.example;

import java.awt.*;

public class CanvasPanel extends Panel {
    public void focusChanged(){
        Component[] components = this.getComponents();
        for(Component i: components){
            if(i instanceof MyRect){
                MyRect j=(MyRect) i;
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


}
