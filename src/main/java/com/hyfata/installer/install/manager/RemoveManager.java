package com.hyfata.installer.install.manager;

import java.io.File;

public class RemoveManager extends InstallManager {
    File file;

    public RemoveManager(File file) {
        this.file = file;
    }

    @Override
    void installing() {

    }
}
