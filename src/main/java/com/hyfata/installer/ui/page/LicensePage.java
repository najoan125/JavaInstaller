package com.hyfata.installer.ui.page;

import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LicensePage extends Page {
    @Override
    void initPanels() {
        header();
        addHeight(30);
        content();
    }

    void header() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("<html><b>" + InfoUtil.parse(InfoUtil.getLicensePage().getString("Text")) + "</b>" +
                "<p>" + InfoUtil.parse(InfoUtil.getLicensePage().getString("SubText")) + "</p>" +
                "</html>"));
        panel.setBorder(new EmptyBorder(10, 10, 0, 0));
        registerPanel(panel);
    }

    void content() {
        JPanel panel = new JPanel();
        panel.add(new JLabel(InfoUtil.parse(InfoUtil.getLicensePage().getString("Content"))));
        registerPanel(panel);
    }
}
