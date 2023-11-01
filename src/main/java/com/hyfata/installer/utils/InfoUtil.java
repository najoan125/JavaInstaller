package com.hyfata.installer.utils;

import com.hyfata.json.JsonReader;
import com.hyfata.json.exceptions.JsonEmptyException;
import org.json.JSONObject;

import java.util.Objects;

public class InfoUtil {
    private static JSONObject info;
    public static void loadInfo() {
        try {
            info = JsonReader.readFromInputStream(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("installerInfo.json")));
        } catch (JsonEmptyException | NullPointerException e) {
            OptionPaneUtil.showErrorDialog("Error during load 'installerInfo.json'.\nPlease contact to developer!","Error during load");
            System.exit(-1);
        }
    }
    public static String getName() {
        return info.getString("Name");
    }
    public static String getVersion() {
        return info.getString("Version");
    }

    // Installer Object
    public static JSONObject getInstallerObject() {
        return info.getJSONObject("Installer");
    }
    public static String getInstallerTitle() {
        return getInstallerObject().getString("Title");
    }
    public static String getInstallerBrandingText() {
        return getInstallerObject().getString("BrandingText");
    }

    // Installer.Lang object
    public static JSONObject getLangObject() {
        return getInstallerObject().getJSONObject("Lang");
    }
    public static String getLangOSNotSupport() {
        return getLangObject().getString("OSNotSupported");
    }
    public static String getLangNext() {
        return getLangObject().getString("Next");
    }
    public static String getLangPrevious() {
        return getLangObject().getString("Previous");
    }
    public static String getLangCancel() {
        return getLangObject().getString("Cancel");
    }

    // Installer.InstallDir Object
    public static JSONObject getInstallDirObject() {
        return getInstallerObject().getJSONObject("InstallDir");
    }
    public static String getInstallDirWindows() {
        return getInstallDirObject().getString("Windows");
    }
    public static String getInstallDirMac() {
        return getInstallDirObject().getString("Mac");
    }
    public static String getInstallDirLinux() {
        return getInstallDirObject().getString("Linux");
    }
    public static boolean isWindowsSupported() {
        return !getInstallDirWindows().equals("none");
    }
    public static boolean isMacSupported() {
        return !getInstallDirMac().equals("none");
    }
    public static boolean isLinuxSupported() {
        return !getInstallDirLinux().equals("none");
    }

    public static JSONObject getWelcomePage() {
        return info.getJSONObject("WelcomePage");
    }
    public static JSONObject getLicensePage() {
        return info.getJSONObject("LicensePage");
    }
    public static JSONObject getDirectoryPage() {
        return info.getJSONObject("DirectoryPage");
    }
    public static JSONObject getInstallPage() {
        return info.getJSONObject("InstallPage");
    }
}
