package com.cleb.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMain extends JFrame {

    private JDesktopPane desktop;

    public ClientMain() {
        super("CLEB - Campus Lab & Equipment Booking System");
        setSize(1100, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        desktop = new JDesktopPane();
        setContentPane(desktop);

        setJMenuBar(createMenuBar());
        setVisible(true);

        openLoginFrame();   // force login on startup
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem loginItem = new JMenuItem("Login");
        loginItem.addActionListener(e -> openLoginFrame());
        fileMenu.add(loginItem);

        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(e -> logout());
        fileMenu.add(logoutItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Booking menu - always visible
        JMenu bookingMenu = new JMenu("Booking");
        JMenuItem bookItem = new JMenuItem("New Booking");
        bookItem.addActionListener(e -> openBookingFrame());
        bookingMenu.add(bookItem);

        // Status menu (renamed from Admin) - always visible
        JMenu statusMenu = new JMenu("Status");
        JMenuItem approveItem = new JMenuItem("Approve / Reject");
        approveItem.addActionListener(e -> openApprovalFrame());
        statusMenu.add(approveItem);

        JMenu viewMenu = new JMenu("View");
        JMenuItem dataItem = new JMenuItem("View Live Data");
        dataItem.addActionListener(e -> openDataViewerFrame());
        viewMenu.add(dataItem);

        menuBar.add(fileMenu);
        menuBar.add(bookingMenu);
        menuBar.add(statusMenu);
        menuBar.add(viewMenu);

        return menuBar;
    }

    private void openLoginFrame() {
        LoginInternalFrame frame = new LoginInternalFrame();
        desktop.add(frame);
        frame.setVisible(true);
    }

    private void openDataViewerFrame() {
        DataViewerInternalFrame frame = new DataViewerInternalFrame();
        desktop.add(frame);
        frame.setVisible(true);
    }

    private void openBookingFrame() {
        BookingInternalFrame frame = new BookingInternalFrame();
        desktop.add(frame);
        frame.setVisible(true);
    }

    private void openApprovalFrame() {
        ApprovalInternalFrame frame = new ApprovalInternalFrame();
        desktop.add(frame);
        frame.setVisible(true);
    }

    private void logout() {
        Session.logout();
        closeAllFrames();
        JOptionPane.showMessageDialog(this, "Logged out successfully");
        openLoginFrame();
    }

    private void closeAllFrames() {
        for (JInternalFrame f : desktop.getAllFrames()) {
            f.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientMain());
    }
}