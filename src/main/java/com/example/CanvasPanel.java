package com.example;

import java.awt.*;

public class CanvasPanel extends Panel {
    public void focusChanged(){
        Component[] components = this.getComponents();
        for(Component i: components){
            if(i instanceof MyComponent){
                MyComponent j=(MyComponent) i;
                j.lostFocus();
            }
        }
    }


}
