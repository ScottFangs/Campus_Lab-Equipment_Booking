package com.cleb.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ClientMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
    private JDesktopPane desktop;

    public ClientMain() {
        super("CLEB - Campus Lab & Equipment Booking System");
        setSize(1100, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        desktop = new JDesktopPane();
        setContentPane(desktop);

        setJMenuBar(createMenuBar());

        setVisible(true);
        openDataViewerFrame();   // show sample data automatically
    }
    
    
    
    
    
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem loginItem = new JMenuItem("Login");
        loginItem.setActionCommand("LOGIN");
        loginItem.addActionListener(this);
        fileMenu.add(loginItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand("EXIT");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);

        JMenu entryMenu = new JMenu("Data Entry");
        JMenuItem labItem = new JMenuItem("Enter New Lab");
        labItem.setActionCommand("NEW_LAB");
        labItem.addActionListener(this);
        entryMenu.add(labItem);

        JMenuItem equipmentItem = new JMenuItem("Enter New Equipment");
        equipmentItem.setActionCommand("NEW_EQUIPMENT");
        equipmentItem.addActionListener(this);
        entryMenu.add(equipmentItem);

        JMenuItem userItem = new JMenuItem("Enter New User");
        userItem.setActionCommand("NEW_USER");
        userItem.addActionListener(this);
        entryMenu.add(userItem);

        JMenu viewMenu = new JMenu("View");
        JMenuItem dataItem = new JMenuItem("View Sample Data");
        dataItem.setActionCommand("VIEW_DATA");
        dataItem.addActionListener(this);
        viewMenu.add(dataItem);

        menuBar.add(fileMenu);
        menuBar.add(entryMenu);
        menuBar.add(viewMenu);
        return menuBar;
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("LOGIN".equals(command)) openLoginFrame();
        else if ("EXIT".equals(command)) System.exit(0);
        else if ("NEW_LAB".equals(command)) openLabEntryFrame();
        else if ("NEW_EQUIPMENT".equals(command)) openEquipmentEntryFrame();
        else if ("NEW_USER".equals(command)) openUserEntryFrame();
        else if ("VIEW_DATA".equals(command)) openDataViewerFrame();
    }

    // The openXXXFrame methods stay the same (copy from your previous version)
    private void openLoginFrame() {
        LoginInternalFrame frame = new LoginInternalFrame();
        desktop.add(frame);
        frame.setVisible(true);
    }

    private void openLabEntryFrame() {
        // LabEntryInternalFrame frame = new LabEntryInternalFrame();
        // desktop.add(frame); frame.setVisible(true);
        JOptionPane.showMessageDialog(this, "Lab Entry frame will open here (add later)");
    }

    private void openEquipmentEntryFrame() {
        JOptionPane.showMessageDialog(this, "Equipment Entry frame will open here (add later)");
    }

    private void openUserEntryFrame() {
        JOptionPane.showMessageDialog(this, "User Entry frame will open here (add later)");
    }

    private void openDataViewerFrame() {
        DataViewerInternalFrame frame = new DataViewerInternalFrame();
        desktop.add(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientMain());
    }
}