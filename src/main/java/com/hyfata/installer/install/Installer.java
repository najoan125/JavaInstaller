package com.hyfata.installer.install;

import com.hyfata.installer.JavaInstaller;
import com.hyfata.installer.install.manager.InstallManager;

import java.util.ArrayList;

public class Installer {
    private static final ArrayList<InstallManager> items = new ArrayList<>();
    private static final ArrayList<String> paths = new ArrayList<>();
    private static String path = null;
    private static int currentIndex = 0;

    public static int getProgress() {
        return currentIndex / items.size() * 100;
    }

    // item
    public static void addItem(InstallManager installManager) {
        if (path == null) {
            path = JavaInstaller.installPath;
        }
        paths.add(path);
        items.add(installManager);
    }

    public static ArrayList<InstallManager> getItems() {
        return items;
    }

    public static void setItemFinished() {
        currentIndex++;
    }

    // path
    public static String getCurrentPath() {
        return paths.get(currentIndex);
    }

    public static void setPath(String path) {
        Installer.path = path;
    }

}
