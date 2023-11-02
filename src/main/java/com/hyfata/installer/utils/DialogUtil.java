package com.hyfata.installer.utils;

import javax.swing.*;

public class DialogUtil {
    public static void showErrorDialog(String content, String title) {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
    }
    public static void showInfoDialog(String content, String title) {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
