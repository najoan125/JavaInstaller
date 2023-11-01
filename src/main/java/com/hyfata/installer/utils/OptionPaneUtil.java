package com.hyfata.installer.utils;

import javax.swing.*;

public class OptionPaneUtil {
    public static void showErrorDialog(String content, String title) {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
    }
}
