package com.hyfata.installer.install.manager;

import java.io.File;

public class FileInstallManager extends InstallManager {
    File file;

    public FileInstallManager(File file) {
        this.file = file;
    }

    @Override
    void installing() {

    }
}
