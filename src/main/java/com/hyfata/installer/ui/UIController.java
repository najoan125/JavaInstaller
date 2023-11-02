package com.hyfata.installer.ui;

import com.hyfata.installer.utils.InfoUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class UIController extends JFrame {
    public static final int WIDTH = 600, HEIGHT = 420;
    public UIController() {
        init(InfoUtil.getInstallerTitle().replace("$[Name]",InfoUtil.getName()).replace("$[Version]",InfoUtil.getVersion()));
        design();
        setVisible(true);
    }

    private void init(String title) {
        setTitle(title);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        String icon = InfoUtil.getInstallerIcon();
        if (!icon.equals("none")) {
            Image img = toolkit.getImage(icon);
            setIconImage(img);
        }

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setResizable(false);
    }

    public static JPanel getScrollablePanel(Component view) {
        JScrollPane scrollPane = new JScrollPane(view);
        JPanel scrollablePanel = new JPanel(new BorderLayout());
        scrollablePanel.add(scrollPane, BorderLayout.CENTER);
        return scrollablePanel;
    }

    private void design() {
        JSONObject welcomePage = InfoUtil.getWelcomePage();
        JSONObject licensePage = InfoUtil.getLicensePage();
        JSONObject directoryPage = InfoUtil.getDirectoryPage();
        JSONObject installPage = InfoUtil.getInstallPage();
    }
}
