package com.hyfata.installer.ui;

import com.hyfata.installer.ui.page.*;
import com.hyfata.installer.utils.DialogUtil;
import com.hyfata.installer.utils.InfoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class UIController extends JFrame {
    public static final int WIDTH = 660, HEIGHT = 420;
    private static final ArrayList<Page> pages = new ArrayList<>();
    private static int currentPageIndex = 0;
    private static int currentHeight = 0;

    public static int getCurrentHeight() {
        return currentHeight;
    }
    public static int getCurrentPageIndex() {
        return currentPageIndex;
    }
    public static Page getPage(int index) {
        if (index+1 > pages.size()) {
            return null;
        }
        return pages.get(index);
    }

    public UIController() {
        init(InfoUtil.getInstallerTitle());
        setVisible(true);
        currentHeight = getContentPane().getSize().height;
        start();
        revalidate();
        repaint();
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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setFocusable(true);
        setResizable(false);
    }

    private void start() {
        initPages();
        add(pages.get(0).getPanel());
        getRootPane().setDefaultButton(Page.next);
        addListeners();
    }

    private void initPages() {
        if (InfoUtil.getWelcomePage() != null) {
            pages.add(new WelcomePage());
        }
        if (InfoUtil.getLicensePage() != null) {
            pages.add(new LicensePage());
        }
        if (InfoUtil.getDirectoryPage() != null) {
            pages.add(new DirectoryPage());
        }
        pages.add(new InstallPage());
        if (InfoUtil.getFinishPage() != null) {
            pages.add(new FinishPage());
        }
    }

    private void addListeners() {
        Page.next.addActionListener(e -> next());
        Page.back.addActionListener(e -> back());
        Page.cancel.addActionListener(e -> cancel());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel();
            }
        });
    }

    private void next() {
        getContentPane().remove(0);
        if (currentPageIndex + 1 < pages.size()) {
            currentPageIndex++;
            getContentPane().add(pages.get(currentPageIndex).getPanel());
            getRootPane().setDefaultButton(Page.next);
            revalidate();
            repaint();
        } else {
            System.exit(1);
        }
    }

    private void back() {
        getContentPane().remove(0);
        currentPageIndex--;
        getContentPane().add(pages.get(currentPageIndex).getPanel());
        getRootPane().setDefaultButton(Page.next);
        revalidate();
        repaint();
    }

    private void cancel() {
        if (DialogUtil.showConfirmDialog(InfoUtil.getLangCancelAlert(), "Installation Cancel")) {
            System.exit(1);
        }
    }
}
