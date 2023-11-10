package com.hyfata.installer;

import com.formdev.flatlaf.IntelliJTheme;
import com.hyfata.installer.ui.UIController;
import com.hyfata.installer.utils.InfoUtil;
import com.hyfata.installer.utils.DialogUtil;
import com.sun.jna.Platform;

public class JavaInstaller {
    public static String installPath;

    public static void main(String[] args) {
        IntelliJTheme.setup(ClassLoader.getSystemClassLoader().getResourceAsStream("theme/arc_theme_dark.theme.json"));
        InfoUtil.loadInfo();
        platformCheck();
        new UIController();
    }

    private static void platformCheck() {
        if (Platform.isWindows() && InfoUtil.isWindowsSupported()) {
            installPath = InfoUtil.getInstallDirWindows();
        } else if (Platform.isMac() && InfoUtil.isMacSupported()) {
            installPath = InfoUtil.getInstallDirMac();
        } else if (Platform.isLinux() && InfoUtil.isLinuxSupported()) {
            installPath = InfoUtil.getInstallDirLinux();
        } else {
            DialogUtil.showErrorDialog(InfoUtil.getLangOSNotSupport(), "OS Not Support Error");
            System.exit(-1);
        }
    }
}
