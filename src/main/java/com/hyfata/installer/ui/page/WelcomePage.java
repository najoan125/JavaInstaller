package com.hyfata.installer.ui.page;

import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import java.awt.*;

public class WelcomePage extends Page {
    @Override
    void initPanels() {
        content();
    }

    void content() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String content = "<html>" +
                "<h1>" + InfoUtil.parse(InfoUtil.getWelcomePage().getString("Title")) + "</h1>" +
                InfoUtil.parse(InfoUtil.getWelcomePage().getString("Content")) +
                "</html>";
        panel.add(Box.createHorizontalStrut(20));
        panel.add(new JLabel(content));
        registerPanel(panel);
    }
}
