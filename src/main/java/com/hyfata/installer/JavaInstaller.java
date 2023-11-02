package com.hyfata.installer;

import com.formdev.flatlaf.IntelliJTheme;
import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.InfoUtil;
import com.hyfata.installer.utils.DialogUtil;
import com.sun.jna.Platform;

public class JavaInstaller {
    public static UIController uiController;
    public static void main(String[] args) {
        IntelliJTheme.setup(ClassLoader.getSystemClassLoader().getResourceAsStream("theme/arc_theme_dark.theme.json"));
        InfoUtil.loadInfo();
        platformCheck();
        uiController = new UIController();
    }

    private static void platformCheck() {
        if (Platform.isWindows() && !InfoUtil.isWindowsSupported()) {
            DialogUtil.showErrorDialog(InfoUtil.getLangOSNotSupport(),"OS Not Support Error");
            System.exit(-1);
        }
        else if (Platform.isMac() && !InfoUtil.isMacSupported()) {
            DialogUtil.showErrorDialog(InfoUtil.getLangOSNotSupport(),"OS Not Support Error");
            System.exit(-1);
        }
        else if (Platform.isLinux() && !InfoUtil.isLinuxSupported()) {
            DialogUtil.showErrorDialog(InfoUtil.getLangOSNotSupport(),"OS Not Support Error");
            System.exit(-1);
        }
    }
}
