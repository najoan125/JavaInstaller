package com.hyfata.installer.ui.page;

import com.hyfata.installer.utils.InfoUtil;

public class InstallPage extends Page {
    @Override
    void initPanels() {
        super.header(InfoUtil.getInstallPage().getString("Text"), InfoUtil.getInstallPage().getString("SubText"));
        addHeight(-5);
        super.horizontalLine();
    }
}
