package com.hyfata.installer.ui.page;

import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.HorizontalLine;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DirectoryPage extends Page {
    @Override
    public void initPanels() {
        header();
        addHeight(-5);
        line();
        content();
    }

    void header() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("<html><b>" + InfoUtil.parse(InfoUtil.getDirectoryPage().getString("Text")) + "</b>" +
                "<p>" + InfoUtil.parse(InfoUtil.getDirectoryPage().getString("SubText")) + "</p>" +
                "</html>"));
        panel.setBorder(new EmptyBorder(0, 10, 0, 0));
        registerPanel(panel);
    }

    void line() {
        JPanel panel = new JPanel();
        HorizontalLine horizontalLine = new HorizontalLine();
        horizontalLine.setPreferredSize(new Dimension(UIController.WIDTH, 2));
        panel.add(horizontalLine);
        registerPanel(panel);
    }

    void content() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("<html><body style='width: " + (UIController.WIDTH - 30) + "'>" + InfoUtil.getDirectoryPage().getString("Content") + "</body></html>");
        panel.add(label);
        registerPanel(panel);
    }
}
