package com.hyfata.installer.utils;

import javax.swing.*;

public class DialogUtil {
    public static void showErrorDialog(String content, String title) {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoDialog(String content, String title) {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean showConfirmDialog(String content, String title) {
        int answer = JOptionPane.showConfirmDialog(null, content, title, JOptionPane.YES_NO_OPTION);
        return answer == JOptionPane.YES_OPTION;
    }
}
