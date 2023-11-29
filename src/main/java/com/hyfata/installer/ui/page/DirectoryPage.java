package com.hyfata.installer.ui.page;

import com.hyfata.installer.JavaInstaller;
import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class DirectoryPage extends Page {
    JTextField installDirField = new JTextField();

    @Override
    public void initPanels() {
        super.header(InfoUtil.getDirectoryPage().getString("Text"), InfoUtil.getDirectoryPage().getString("SubText"));
        addHeight(-5);
        super.horizontalLine();
        content();
        addHeight(20);
        directory();
    }

    void content() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("<html><body style='width: " + (UIController.WIDTH - 50) + "'>" + InfoUtil.getDirectoryPage().getString("Content") + "</body></html>");
        panel.add(label);
        panel.setBorder(new EmptyBorder(0, 20, 0, 0));
        registerPanel(panel);
    }

    void directory() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.add(new JLabel(InfoUtil.getDirectoryPage().getString("InstallPath")));

        installDirField.setText(JavaInstaller.installPath);
        installDirField.setPreferredSize(new Dimension(350, installDirField.getPreferredSize().height));
        installDirField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                onChange();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                onChange();
            }

            private void onChange() {
                JavaInstaller.installPath = installDirField.getText().trim();
            }
        });
        panel.add(installDirField);

        JButton jButton = new JButton();
        jButton.setText(InfoUtil.getDirectoryPage().getString("Browse"));
        jButton.addActionListener(this::browseButtonListener);
        panel.add(jButton);

        registerPanel(panel);
    }

    void browseButtonListener(ActionEvent e) {
        showDirectoryChooser();
    }

    private void showDirectoryChooser() {
        JFileChooser fileChooser = new JFileChooser();
        File installDir = new File(JavaInstaller.installPath);
        if (!installDir.exists()) {
            installDir = installDir.getParentFile();
        }
        fileChooser.setCurrentDirectory(installDir);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
            installDirField.setText(selectedDirectory.trim());
        }
    }
}
