package com.hyfata.installer.install.manager;

import com.hyfata.installer.install.Installer;
import com.hyfata.installer.ui.page.InstallPage;

public abstract class InstallManager {
    abstract void installing();
    public void install() {
        installing();
        Installer.setItemFinished();
        InstallPage.changeProgress();
        InstallPage.setSubProgressVisible(false);
    }
}
