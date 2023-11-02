package com.hyfata.installer.ui;

import com.hyfata.installer.ui.page.Page;
import com.hyfata.installer.ui.page.WelcomePage;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import java.awt.*;

public class UIController extends JFrame {
    public static final int WIDTH = 520, HEIGHT = 320;
    public UIController() {
        init(InfoUtil.getInstallerTitle().replace("$[Name]",InfoUtil.getName()).replace("$[Version]",InfoUtil.getVersion()));
        start();
        setVisible(true);
    }

    private void init(String title) {
        setTitle(title);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        String icon = InfoUtil.getInstallerIcon();
        if (!icon.equals("none")) {
            Image img = toolkit.getImage(icon);
            setIconImage(img);
        }

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setResizable(false);
    }

    private void start() {
        Page welcome = new WelcomePage();
        add(welcome.getPanel());
        getRootPane().setDefaultButton(Page.next);
    }
}
