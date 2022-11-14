package com.example.Component;

import com.example.graphics.*;

import javax.swing.*;
import java.awt.*;

public  class CanvasPanel extends JPanel {

    private MyFrame frame=null;
    public void focusChanged(){
        Component[] components = this.getComponents();
        for(Component i: components){
            if(i instanceof MyComponent){
                MyComponent j=(MyComponent) i;
                j.lostFocus();
            }
        }
    }

    public CanvasPanel(MyFrame frame){
        this.frame=frame;
        this.setBounds(0,0,970,820);
        this.setBackground(new Color(255,255,255));
    }
    public CanvasPanel(){
        this.setBounds(0,0,970,820);
        this.setBackground(new Color(255,255,255));
    }

}
