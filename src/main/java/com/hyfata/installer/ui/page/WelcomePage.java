package com.hyfata.installer.ui.page;

import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;

public class WelcomePage extends Page {
    @Override
    void initPanels() {
        setHeight(UIController.HEIGHT-100);
        content();
        buttons();
    }

    void content() {
        JPanel panel = new JPanel();
        String content = "<html>" +
                "<h1>" + InfoUtil.getWelcomePage().getString("Title") + "</h1>" +
                "<br>" +
                InfoUtil.getWelcomePage().getString("Content") +
                "</html>";
        panel.add(Box.createHorizontalStrut(5));
        panel.add(new JLabel(content));
        registerPanel(panel);
    }

    void buttons() {
        JPanel panel = new JPanel();
        panel.add(next);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(cancel);
        registerNaviButtonPanel(panel);
    }
}
