package com.hyfata.installer.ui.page;

import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.HorizontalLine;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LicensePage extends Page {
    @Override
    void initPanels() {
        header();
        line();
        addHeight(10);
        content();
    }

    void header() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("<html><b>" + InfoUtil.parse(InfoUtil.getLicensePage().getString("Text")) + "</b>" +
                "<p>" + InfoUtil.parse(InfoUtil.getLicensePage().getString("SubText")) + "</p>" +
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
        JPanel panel = new JPanel();
        panel.add(new JLabel(InfoUtil.parse(InfoUtil.getLicensePage().getString("Content"))));
        registerPanel(panel);
    }
}
