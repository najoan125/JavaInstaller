package com.hyfata.installer.ui.page;

import com.hyfata.file.utils.FileUtil;
import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.HorizontalLine;
import com.hyfata.installer.utils.InfoUtil;
import com.hyfata.installer.utils.ScrollPaneUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Objects;

public class LicensePage extends Page {
    private static boolean read = false;
    private static boolean agree = false;
    @Override
    void initPanels() {
        header();
        addHeight(-5);
        line();
        addHeight(15);
        content();
        addHeight(-5);
        license();
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

    void license() {
        //license JLabel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String content = FileUtil.readFromInputStream(Objects.requireNonNull(
                ClassLoader.getSystemClassLoader().getResourceAsStream(InfoUtil.getLicensePage().getString("LicenseFile"))));
        panel.add(new JLabel("<html><body style='width: " + (UIController.WIDTH - 55) + "'>" + content.replace("\n", "<br>") + "</body></html>"));

        boolean checkbox = InfoUtil.getLicensePage().getBoolean("AgreeCheckbox");
        // change next button
        setNextChanging();
        if (!checkbox) {
            next.setText(InfoUtil.getLicensePage().getString("AgreeButton"));
        } else if (!read) {
            next.setText(getNextString());
            next.setEnabled(false);
        } else {
            next.setText(getNextString());
        }

        //scrollPane(License content)
        int height = checkbox ? getRemainingHeight() - 60 : getRemainingHeight() - 30;
        JPanel result = ScrollPaneUtil.getScrollPane(panel, new FlowLayout(FlowLayout.CENTER), UIController.WIDTH - 30, height);
        if (!checkbox && !read) {
            next.setEnabled(false);
            Objects.requireNonNull(ScrollPaneUtil.getScrollPaneFromJPanel(result)).getViewport().addChangeListener(this::onScroll);
        }
        registerPanel(result);

        //Agree checkbox
        if (checkbox) {
            addHeight(-5);
            registerPanel(checkbox());
        }
    }

    JPanel checkbox() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JCheckBox checkBox = new JCheckBox(InfoUtil.getLicensePage().getString("Agree"));
        if (agree) {
            checkBox.setSelected(true);
        }

        checkBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                next.setEnabled(true);
                read = true;
                agree = true;
            } else {
                next.setEnabled(false);
                read = false;
                agree = false;
            }
        });

        panel.setBorder(new EmptyBorder(0, 0, 0, 15));
        panel.add(checkBox);
        return panel;
    }

    void onScroll(ChangeEvent e) {
        JViewport viewport = (JViewport) e.getSource();
        int extentHeight = viewport.getExtentSize().height;
        int viewHeight = viewport.getViewSize().height;
        int verticalPosition = viewport.getViewPosition().y;

        if (verticalPosition + extentHeight >= viewHeight) {
            read = true;
            next.setEnabled(true);
        }
    }
}
