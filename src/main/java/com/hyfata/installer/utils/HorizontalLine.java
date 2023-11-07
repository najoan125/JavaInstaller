package com.hyfata.installer.utils;

import javax.swing.*;
import java.awt.*;

public class HorizontalLine extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth(); // JPanel의 가로 길이
        int panelHeight = getHeight();

        // 가로 줄 그리기
        //g.drawLine(0, 0, panelWidth, 0);
        g.fillRect(0, 0, panelWidth, panelHeight);
    }
}
