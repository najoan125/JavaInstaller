package com.hyfata.installer.ui.page;

import com.hyfata.installer.install.Installer;
import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class InstallPage extends Page {
    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private static JProgressBar progress;
    private static JLabel progressText;
    @Override
    void initPanels() {
        super.header(InfoUtil.getInstallPage().getString("Text"), InfoUtil.getInstallPage().getString("SubText"));
        addHeight(-5);
        super.horizontalLine();
        progressBar();
        super.setNextChanging();
        next.setEnabled(false);
        back.setEnabled(false);
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
            executorService.shutdown();
            next.setEnabled(true);
        }
        progressText.setText(Installer.getProgress() + "%");
        progress.setValue(Installer.getProgress());
    }
}
