package com.hyfata.installer.ui.page;

import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import java.awt.*;

public class LicensePage extends Page {
    @Override
    void initPanels() {
        header();
    }

    void header() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("<html><b>" + InfoUtil.parse(InfoUtil.getLicensePage().getString("Text")) + "</b><br></html>"));
        panel.add(new JLabel(InfoUtil.parse(InfoUtil.getLicensePage().getString("SubText"))));
        registerPanel(panel);
    }
}
