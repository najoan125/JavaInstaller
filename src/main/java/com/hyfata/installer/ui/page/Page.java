package com.hyfata.installer.ui.page;

import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Page {
    //panels
    private final ArrayList<JPanel> panels = new ArrayList<>();
    private JPanel naviButtons;

    //height
    private final HashMap<Integer,Integer> addedHeights = new HashMap<>(); //index, height
    private int height = 35;

    //layout
    private LayoutManager layoutManager = new FlowLayout(FlowLayout.LEFT);
    private LayoutManager subLayout = new FlowLayout(FlowLayout.LEFT);

    //buttons
    public static JButton back = new JButton(InfoUtil.getLangPrevious());
    public static JButton next = new JButton(InfoUtil.getLangNext());
    public static JButton cancel = new JButton(InfoUtil.getLangCancel());

    abstract void initPanels();
    public JPanel getPanel(){
        JPanel panel = new JPanel(layoutManager);
        initPanels();

        int i = 0;
        for (JPanel p : panels) {
            p.setLayout(subLayout);
            p.setPreferredSize(new Dimension(UIController.WIDTH, height));
            if (addedHeights.containsKey(i)){
                p.setPreferredSize(new Dimension(UIController.WIDTH,height+addedHeights.get(i)));
            }
            panel.add(p);

            i++;
        }
        JPanel navi = new JPanel();
        JLabel branding = new JLabel(InfoUtil.getInstallerBrandingText());
        branding.setEnabled(false);

        naviButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        naviButtons.setPreferredSize(new Dimension(UIController.WIDTH-branding.getPreferredSize().width-30, 35));
        navi.add(branding);
        navi.add(naviButtons);
        panel.add(navi);
        return panel;
    }

    //register
    protected void registerPanel(JPanel panel) {
        panels.add(panel);
    }
    protected void registerNaviButtonPanel(JPanel panel) {
        naviButtons = panel;
    }

    //layout
    protected void setLayout(LayoutManager layout) {
        layoutManager = layout;
    }
    protected void setLayoutEachPanels(LayoutManager layout) {
        subLayout = layout;
    }

    //height
    protected void setHeight(int height) {
        this.height = height;
    }
    protected void addHeight(int height) {
        addedHeights.put(panels.size()-1, height);
    }
}
