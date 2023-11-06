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
            DialogUtil.showErrorDialog("Can't found 'installerInfo.json'.\nPlease contact to developer!","Error during load");
            System.exit(-1);
        }
    }
    public static String parse(String str) {
        return str.replace("$[Name]",InfoUtil.getName()).replace("$[Version]",InfoUtil.getVersion());
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
        return parse(getInstallerObject().getString("Title"));
    }
    public static String getInstallerIcon() {
        return getInstallerObject().getString("Icon");
    }
    public static String getInstallerBrandingText() {
        return parse(getInstallerObject().getString("BrandingText"));
    }

    // Installer.Lang object
    public static JSONObject getLangObject() {
        return getInstallerObject().getJSONObject("Lang");
    }
    public static String getLangOSNotSupport() {
        return parse(getLangObject().getString("OSNotSupported"));
    }
    public static String getLangNext() {
        return getLangObject().getString("Next");
    }
    public static String getLangInstall() {
        return getLangObject().getString("Install");
    }
    public static String getLangFinish() {
        return getLangObject().getString("Finish");
    }
    public static String getLangPrevious() {
        return getLangObject().getString("Previous");
    }
    public static String getLangCancel() {
        return getLangObject().getString("Cancel");
    }
    public static String getLangCancelAlert() {
        return parse(getLangObject().getString("CancelAlert"));
    }

    // Installer.InstallDir Object
    public static JSONObject getInstallDirObject() {
        return getInstallerObject().getJSONObject("InstallDir");
    }
    public static String getInstallDirWindows() {
        return parse(getInstallDirObject().getString("Windows"));
    }
    public static String getInstallDirMac() {
        return parse(getInstallDirObject().getString("Mac"));
    }
    public static String getInstallDirLinux() {
        return parse(getInstallDirObject().getString("Linux"));
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
        return info.optJSONObject("WelcomePage", null);
    }
    public static JSONObject getLicensePage() {
        return info.optJSONObject("LicensePage", null);
    }
    public static JSONObject getDirectoryPage() {
        return info.optJSONObject("DirectoryPage", null);
    }
    public static JSONObject getInstallPage() {
        return info.getJSONObject("InstallPage");
    }
    public static JSONObject getFinishPage() {
        return info.getJSONObject("FinishPage");
    }
}
