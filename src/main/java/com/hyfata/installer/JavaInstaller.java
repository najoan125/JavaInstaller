package com.hyfata.installer;

import com.formdev.flatlaf.IntelliJTheme;
import com.hyfata.installer.utils.InfoUtil;
import com.hyfata.installer.utils.OptionPaneUtil;
import com.sun.jna.Platform;

public class JavaInstaller {
    public static void main(String[] args) {
        IntelliJTheme.setup(ClassLoader.getSystemClassLoader().getResourceAsStream("theme/arc_theme_dark.theme.json"));
        InfoUtil.loadInfo();
        PlatformCheck();
    }

    private static void PlatformCheck() {
        if (Platform.isWindows() && !InfoUtil.isWindowsSupported()) {
            OptionPaneUtil.showErrorDialog(InfoUtil.getLangOSNotSupport(),"OS Not Support Error");
            System.exit(-1);
        }
        else if (Platform.isMac() && !InfoUtil.isMacSupported()) {
            OptionPaneUtil.showErrorDialog(InfoUtil.getLangOSNotSupport(),"OS Not Support Error");
            System.exit(-1);
        }
        else if (Platform.isLinux() && !InfoUtil.isLinuxSupported()) {
            OptionPaneUtil.showErrorDialog(InfoUtil.getLangOSNotSupport(),"OS Not Support Error");
            System.exit(-1);
        }
    }
}
