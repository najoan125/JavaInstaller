package com.hyfata.installer.ui.page;

import com.hyfata.installer.JavaInstaller;
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
        addHeight(20);
        directory();
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
        JLabel label = new JLabel("<html><body style='width: " + (UIController.WIDTH - 50) + "'>" + InfoUtil.getDirectoryPage().getString("Content") + "</body></html>");
        panel.add(label);
        registerPanel(panel);
    }

    void directory() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextField jTextField = new JTextField();
        jTextField.setText(JavaInstaller.installPath);
        jTextField.setPreferredSize(new Dimension(400, jTextField.getPreferredSize().height));
        panel.add(jTextField);

        JButton jButton = new JButton();
        jButton.setText(InfoUtil.getDirectoryPage().getString("Browse"));
        panel.add(jButton);

        registerPanel(panel);
    }
}
