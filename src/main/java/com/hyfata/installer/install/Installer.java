package com.hyfata.installer.install;

import com.hyfata.installer.install.manager.InstallManager;

import java.util.ArrayList;

public class Installer {
    private static boolean installing = false;
    private static boolean installed = false;
    private static final ArrayList<InstallManager> totalItems = new ArrayList<>();
    private static int currentIndex = 0;

    public static int getProgress() {
        return currentIndex / totalItems.size() * 100;
    }

    // item
    public static void addItem(InstallManager installManager) {
        totalItems.add(installManager);
    }

    public static ArrayList<InstallManager> getTotalItems() {
        return totalItems;
    }

    public static void setItemFinished() {
        currentIndex++;
    }

    // other
    public static boolean isInstalling() {
        return installing;
    }

    public static void setInstalling(boolean isEnabled) {
        installing = isEnabled;
    }

    public static boolean isInstalled() {
        return installed;
    }

    public static void setInstalled(boolean isEnabled) {
        installed = isEnabled;
    }
}
