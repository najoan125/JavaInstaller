package com.hyfata.installer.ui.page;

import com.hyfata.installer.ui.UIController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Page {
    private final ArrayList<JPanel> panels = new ArrayList<>();
    private final HashMap<Integer,Integer> addedHeights = new HashMap<>(); //index, height
    private int height = 35;
    private LayoutManager layoutManager = new FlowLayout(FlowLayout.CENTER);
    public JPanel getPanel(){
        JPanel panel = new JPanel(layoutManager);
        initPanels();

        int i = 0;
        for (JPanel p : panels) {
            p.setLayout(new FlowLayout(FlowLayout.CENTER));
            p.setPreferredSize(new Dimension(UIController.WIDTH, height));
            if (addedHeights.containsKey(i)){
                p.setPreferredSize(new Dimension(UIController.WIDTH,height+addedHeights.get(i)));
            }
            panel.add(p);

            i++;
        }
        return panel;
    }
    protected void registerPanel(JPanel panel) {
        panels.add(panel);
    }
    protected void setLayout(LayoutManager layout) {
        layoutManager = layout;
    }
    protected void setHeight(int height) {
        this.height = height;
    }
    protected void addHeight(int height) {
        addedHeights.put(panels.size()-1, height);
    }
    abstract void initPanels();
}
