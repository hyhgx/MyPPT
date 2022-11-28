package com.example.Component;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class CanvasPanels extends Panel {

    private final MyFrame frame;
    private final List<CanvasPanel> panels = new LinkedList<CanvasPanel>();

    private CanvasPanel currentPanel;

    public CanvasPanels(MyFrame frame) {
        this.setLayout(null);
        this.frame = frame;
    }

    public CanvasPanel getCurrentPanel() {
        return currentPanel;
    }

    public void addPanel() {
        panels.add(new CanvasPanel(frame));
    }

    public void addPanel(CanvasPanel p) {
        panels.add(p);
    }

    public void deletePanel(int index) {
        panels.remove(index);
    }

    public CanvasPanel getPanel(int index) {
        return panels.get(index);
    }

    public List<CanvasPanel> returnPanels() {
        return panels;
    }

    public void changeCurrentPanel(CanvasPanel newPanel) {
        if (currentPanel != null) {
            currentPanel.focusChanged();
            currentPanel.updateLeftImage();
            this.remove(currentPanel);
        }
        if (newPanel != null) {
            this.add(newPanel);
        }
        currentPanel = newPanel;
        if (currentPanel != null) {
            currentPanel.requestFocus(true);
        }
        this.repaint();
    }

    public int getPanelsSize() {
        return panels.size();
    }

    public void clear() {
        super.removeAll();
        panels.clear();
    }

    public int getIndex(CanvasPanel panel){
        return panels.indexOf(panel);
    }
}
