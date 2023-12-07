package com.hyfata.installer.ui.page;

import com.hyfata.installer.install.Installer;
import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import java.awt.*;

public class InstallPage extends Page {
    private static JProgressBar progress;
    private static JLabel progressText;
    private static JLabel text;
    @Override
    void initPanels() {
        super.header(InfoUtil.getInstallPage().getString("Text"), InfoUtil.getInstallPage().getString("SubText"));
        addHeight(-5);
        super.horizontalLine();
        content();
        addHeight(-5);
        progressBar();
        super.setNextChanging();
        next.setEnabled(false);
        back.setEnabled(false);
    }

    void content() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        text = new JLabel(InfoUtil.getInstallPage().getString("Loading"));
        panel.add(text);
        registerPanel(panel);
    }

    void progressBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        progress = new JProgressBar(0, 100);
        progress.setPreferredSize(new Dimension(UIController.WIDTH - 100, 20));
        panel.add(progress);
        panel.add(progressText = new JLabel(progress.getValue() + "%"));
        registerPanel(panel);
    }

    public static void changeProgress() {
        if (Installer.getProgress() >= 100) {
            next.setEnabled(true);
        }
        progressText.setText(Installer.getProgress() + "%");
        progress.setValue(Installer.getProgress());
    }

    public static void setContentText(String contentText) {
        text.setText(contentText);
    }
}
