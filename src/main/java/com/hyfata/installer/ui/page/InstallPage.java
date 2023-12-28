package com.hyfata.installer.ui.page;

import com.hyfata.installer.install.Installer;
import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import java.awt.*;

public class InstallPage extends Page {
    private static JProgressBar progress;
    private static JProgressBar subProgress;

    // show percent
    private static JLabel progressText;
    private static JLabel subProgressText;

    private static JLabel text;
    private static JLabel subText;

    @Override
    void initPanels() {
        super.header(InfoUtil.getInstallPage().getString("Text"), InfoUtil.getInstallPage().getString("SubText"));
        addHeight(-5);
        super.horizontalLine();

        content();
        addHeight(-5);
        progressBar();

        addHeight(50);
        subContent();
        addHeight(-5);
        subProgressBar();

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
        panel.add(progressText = new JLabel("0%"));
        registerPanel(panel);
    }

    void subContent() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subText = new JLabel(InfoUtil.getInstallPage().getString("Loading"));
        subText.setVisible(false);
        panel.add(subText);
        registerPanel(panel);
    }

    void subProgressBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subProgress = new JProgressBar(0, 100);
        subProgress.setPreferredSize(new Dimension(UIController.WIDTH - 200, 20));
        subProgress.setVisible(false);
        panel.add(subProgress);

        subProgressText = new JLabel("0%");
        subProgressText.setVisible(false);
        panel.add(subProgressText);
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

    public static void setSubProgress(String text, int progress) {
        subText.setText(text);
        subProgressText.setText(progress + "%");
        subProgress.setValue(progress);
        setSubProgressVisible(true);
    }

    public static void setSubProgressVisible(boolean visible) {
        subText.setVisible(visible);
        subProgress.setVisible(visible);
        subProgressText.setVisible(visible);
    }
}
