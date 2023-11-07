package com.hyfata.installer.utils;

import javax.swing.*;
import java.awt.*;

public class HorizontalLine extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        g.fillRect(0, 0, panelWidth, panelHeight);
    }
}
