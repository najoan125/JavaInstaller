package com.hyfata.installer.ui.page;

import com.hyfata.file.utils.FileUtil;
import com.hyfata.installer.ui.UIController;
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
    private final boolean showCheckbox = InfoUtil.getLicensePage().getBoolean("AgreeCheckbox");

    @Override
    void initPanels() {
        changeNextButton();

        super.header(InfoUtil.getLicensePage().getString("Text"), InfoUtil.getLicensePage().getString("SubText"));
        addHeight(-5);
        super.horizontalLine();
        addHeight(15);
        content();
        addHeight(-5);
        license();
    }

    void changeNextButton() {
        setNextChanging();
        if (!showCheckbox) {
            next.setText(InfoUtil.getLicensePage().getString("AgreeButton"));
        } else if (!read) {
            next.setText(getNextString());
            next.setEnabled(false);
        } else {
            next.setText(getNextString());
        }
    }

    void content() {
        JPanel panel = new JPanel();
        panel.add(new JLabel(InfoUtil.parse(InfoUtil.getLicensePage().getString("Content"))));
        registerPanel(panel);
    }

    void license() {
        addLicensePane();

        //Add agree checkbox
        if (showCheckbox) {
            addHeight(-5);
            addCheckbox();
        }
    }

    void addLicensePane() {
        int width = UIController.WIDTH - 30;
        int height = showCheckbox ? getRemainingHeight() - 60 : getRemainingHeight() - 30;

        JPanel result = ScrollPaneUtil.getScrollPane(getLicenseTextArea(), new FlowLayout(FlowLayout.CENTER), width, height);
        if (!showCheckbox && !read) {
            next.setEnabled(false);
            JScrollPane scrollPane = Objects.requireNonNull(ScrollPaneUtil.getScrollPaneFromJPanel(result));
            scrollPane.getViewport().addChangeListener(this::onScroll);
        }
        registerPanel(result);
    }

    JTextArea getLicenseTextArea() {
        String content = FileUtil.readFromInputStream(Objects.requireNonNull(
                ClassLoader.getSystemClassLoader().getResourceAsStream(InfoUtil.getLicensePage().getString("LicenseFile"))));
        JTextArea textArea = new JTextArea(content);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        return textArea;
    }

    void addCheckbox() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JCheckBox checkBox = new JCheckBox(InfoUtil.getLicensePage().getString("Agree"));
        if (agree) {
            checkBox.setSelected(true);
        }
        checkBox.addItemListener(this::checkboxListener);
        panel.setBorder(new EmptyBorder(0, 0, 0, 15));
        panel.add(checkBox);
        registerPanel(panel);
    }

    void checkboxListener(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            next.setEnabled(true);
            read = true;
            agree = true;
        } else {
            next.setEnabled(false);
            read = false;
            agree = false;
        }
    }

    void onScroll(ChangeEvent e) {
        JViewport viewport = (JViewport) e.getSource();
        int extentHeight = viewport.getExtentSize().height;
        int viewHeight = viewport.getViewSize().height;
        int verticalPosition = viewport.getViewPosition().y;

        if (verticalPosition + extentHeight >= viewHeight) {
            read = true;
            next.setEnabled(true);
        } else {
            read = false;
            next.setEnabled(false);
        }
    }
}
