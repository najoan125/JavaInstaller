package com.hyfata.installer.utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ScrollPaneUtil {
    public static JPanel getScrollPane(JPanel panel) {
        JScrollPane jScrollPane = new JScrollPane(panel);
        Border border = new LineBorder(Color.decode("#4e5467"), 2);
        jScrollPane.setBorder(border);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        JPanel result = new JPanel();
        result.add(jScrollPane);
        return result;
    }
    public static JPanel getScrollPane(JPanel panel, int width, int height) {
        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setPreferredSize(new Dimension(width, height));
        Border border = new LineBorder(Color.decode("#4e5467"), 2);
        jScrollPane.setBorder(border);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        JPanel result = new JPanel();
        result.add(jScrollPane);
        return result;
    }

    public static JPanel getScrollPane(JPanel panel, LayoutManager layout) {
        JScrollPane jScrollPane = new JScrollPane(panel);
        Border border = new LineBorder(Color.decode("#4e5467"), 2);
        jScrollPane.setBorder(border);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        JPanel result = new JPanel(layout);
        result.add(jScrollPane);
        return result;
    }
    public static JPanel getScrollPane(JPanel panel, LayoutManager layout, int width, int height) {
        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setPreferredSize(new Dimension(width, height));
        Border border = new LineBorder(Color.decode("#4e5467"), 2);
        jScrollPane.setBorder(border);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        JPanel result = new JPanel(layout);
        result.add(jScrollPane);
        return result;
    }

    public static JScrollPane getScrollPaneFromJPanel(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JScrollPane) {
                return (JScrollPane) component;
            } else if (component instanceof Container) {
                JScrollPane foundScrollPane = getScrollPaneFromJPanel((Container) component);
                if (foundScrollPane != null) {
                    return foundScrollPane;
                }
            }
        }
        return null;
    }
}
