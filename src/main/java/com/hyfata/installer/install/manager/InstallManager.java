package com.hyfata.installer.install.manager;

import com.hyfata.installer.install.Installer;

public abstract class InstallManager {
    abstract void installing();
    public void install() {
        installing();
        Installer.setItemFinished();
    }
}
