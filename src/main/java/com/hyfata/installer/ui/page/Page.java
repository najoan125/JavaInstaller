package com.hyfata.installer.ui.page;

import com.hyfata.installer.JavaInstaller;
import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Page {
    //panels
    private final ArrayList<JPanel> panels = new ArrayList<>();
    private JPanel naviButtons;

    //height
    private final HashMap<Integer, Integer> addedHeights = new HashMap<>(); //index, height

    //buttons
    public static JButton back = new JButton(InfoUtil.getLangPrevious());
    public static JButton next = new JButton(InfoUtil.getLangNext());
    public static JButton cancel = new JButton(InfoUtil.getLangCancel());
    private boolean nextEnabled = true;

    //interface
    abstract void initPanels();

    //getPanel
    public JPanel getPanel() {
        initPanels();
        JPanel result = new JPanel(new BorderLayout());

        int i = 0;
        for (JPanel p : panels) {
            if (addedHeights.containsKey(i)) {
                p.setPreferredSize(new Dimension(UIController.WIDTH, p.getPreferredSize().height + addedHeights.get(i)));
            } else {
                p.setPreferredSize(new Dimension(UIController.WIDTH, p.getPreferredSize().height));
            }
            result.add(p);
            i++;
        }

        result.add(getNaviPanel(), BorderLayout.SOUTH);
        return result;
    }
    private JPanel getNaviPanel() {
        JPanel result = new JPanel(new BorderLayout());

        //branding
        JLabel branding = new JLabel(InfoUtil.getInstallerBrandingText());
        branding.setEnabled(false);
        result.add(branding, BorderLayout.WEST);

        //back,next,cancel button
        registerNaviButtonPanel();
        result.add(naviButtons, BorderLayout.EAST);

        result.setBorder(new EmptyBorder(0, 15, 15, 15));
        return result;
    }

    //button
    protected void setNextEnabled(boolean enabled) {
        nextEnabled = enabled;
    }
    protected void setAllEnabled(boolean enabled) {
        next.setEnabled(enabled);
        back.setEnabled(enabled);
        cancel.setEnabled(enabled);
    }

    //register
    protected void registerPanel(JPanel panel) {
        panels.add(panel);
    }
    private void registerNaviButtonPanel() {
        JPanel panel = new JPanel();
        if (JavaInstaller.uiController != null) {
            int currentIndex = JavaInstaller.uiController.getCurrentPageIndex();

            //nextPage == InstallPage
            if (JavaInstaller.uiController.getPage(currentIndex + 1) instanceof InstallPage) {
                next.setText(InfoUtil.getLangInstall());
            }
            //currentPage == InstallPage
            else if (JavaInstaller.uiController.getPage(currentIndex) instanceof InstallPage) {
                if (JavaInstaller.uiController.getPage(currentIndex + 1) == null) {
                    next.setText(InfoUtil.getLangFinish());
                } else {
                    next.setText(InfoUtil.getLangNext());
                }
                //setAllEnabled(false);
            }
            //currentPage == FinishPage
            else if (JavaInstaller.uiController.getPage(currentIndex) instanceof FinishPage) {
                next.setText(InfoUtil.getLangFinish());
            } else {
                next.setText(InfoUtil.getLangNext());
            }
            //currentPage != FirstPage
            if (currentIndex != 0 && JavaInstaller.uiController.getPage(currentIndex + 1) != null) {
                panel.add(back);
            }
        }
        if (!nextEnabled) {
            next.setEnabled(false);
        }
        panel.add(next);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(cancel);
        naviButtons = panel;
    }

    //height
    protected void addHeight(int height) {
        addedHeights.put(panels.size() - 1, height);
    }
}
