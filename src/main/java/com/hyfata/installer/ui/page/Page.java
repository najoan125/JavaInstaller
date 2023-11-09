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
    private int allHeights = 0;

    //buttons
    public static JButton back = new JButton(InfoUtil.getLangPrevious());
    public static JButton next = new JButton(InfoUtil.getLangNext());
    public static JButton cancel = new JButton(InfoUtil.getLangCancel());
    private boolean nextChanged = false;

    //interface
    abstract void initPanels();

    //Panel methods
    public JPanel getPanel() {
        panels.clear();
        addedHeights.clear();
        allHeights = 0;
        initPanels();

        JPanel result = new JPanel(new BorderLayout());
        result.add(createContentPanel());
        result.add(getNaviPanel(), BorderLayout.SOUTH);
        return result;
    }

    private JPanel createContentPanel() {
        int i = 0;
        JPanel in = new JPanel();
        for (JPanel p : panels) {
            if (addedHeights.containsKey(i)) {
                p.setPreferredSize(new Dimension(UIController.WIDTH, p.getPreferredSize().height + addedHeights.get(i)));
            } else {
                p.setPreferredSize(new Dimension(UIController.WIDTH, p.getPreferredSize().height));
            }
            in.add(p);
            i++;
        }
        return in;
    }

    private JPanel getNaviPanel() {
        JPanel result = new JPanel(new BorderLayout());

        //branding
        JLabel branding = new JLabel(InfoUtil.getInstallerBrandingText());
        branding.setEnabled(false);
        result.add(branding, BorderLayout.WEST);

        //back,next,cancel button
        initNaviPanel();
        result.add(naviButtons, BorderLayout.EAST);

        result.setBorder(new EmptyBorder(0, 15, 15, 15));
        return result;
    }

    private void initNaviPanel() {
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
                    cancel.setEnabled(false);
                } else {
                    next.setText(InfoUtil.getLangNext());
                }
            }
            //currentPage == FinishPage
            else if (JavaInstaller.uiController.getPage(currentIndex) instanceof FinishPage) {
                next.setText(InfoUtil.getLangFinish());
                cancel.setEnabled(false);
            } else if (!nextChanged) {
                next.setText(InfoUtil.getLangNext());
                next.setEnabled(true);
            }
            //currentPage != FirstPage
            if (currentIndex != 0 && JavaInstaller.uiController.getPage(currentIndex + 1) != null) {
                panel.add(back);
            }
        }
        panel.add(next);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(cancel);
        this.naviButtons = panel;
    }

    //utils
    protected void setNaviButtonAllEnabled(boolean enabled) {
        next.setEnabled(enabled);
        back.setEnabled(enabled);
        cancel.setEnabled(enabled);
    }

    protected int getRemainingHeight() {
        int height = 0;
        for (JPanel p : panels) {
            height += p.getPreferredSize().height + 5;
        }
        height += getNaviPanel().getPreferredSize().height;
        height += allHeights;
        return UIController.getCurrentHeight() - height;
    }

    protected void setNextChanged(boolean changed) {
        nextChanged = changed;
    }

    protected String getNextString() {
        int currentIndex = JavaInstaller.uiController.getCurrentPageIndex();

        //nextPage == InstallPage
        if (JavaInstaller.uiController.getPage(currentIndex + 1) instanceof InstallPage) {
            return InfoUtil.getLangInstall();
        }
        //currentPage == InstallPage
        else if (JavaInstaller.uiController.getPage(currentIndex) instanceof InstallPage) {
            if (JavaInstaller.uiController.getPage(currentIndex + 1) == null) {
                return InfoUtil.getLangFinish();
            } else {
                return InfoUtil.getLangNext();
            }
        }
        //currentPage == FinishPage
        else if (JavaInstaller.uiController.getPage(currentIndex) instanceof FinishPage) {
            return InfoUtil.getLangFinish();
        }
        return InfoUtil.getLangNext();
    }

    //register
    protected void registerPanel(JPanel panel) {
        panels.add(panel);
    }

    //height
    protected void addHeight(int height) {
        allHeights += height;
        addedHeights.put(panels.size() - 1, height);
    }
}
