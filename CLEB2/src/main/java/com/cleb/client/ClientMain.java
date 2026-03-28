package com.cleb.client;

import com.cleb.common.Request;
import com.cleb.model.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ClientMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
    private JDesktopPane desktop;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ClientListener listenerThread;

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

        JMenu viewMenu = new JMenu("View");
        JMenuItem dataItem = new JMenuItem("View Live Data");
        dataItem.addActionListener(e -> openDataViewerFrame());
        viewMenu.add(dataItem);

        menuBar.add(fileMenu);
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

    // Called from LoginInternalFrame after successful login
    public void onSuccessfulLogin(User user) {
        try {
            socket = new Socket("localhost", 5000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Send login request
            Request loginRequest = new Request("LOGIN", user);
            out.writeObject(loginRequest);
            out.flush();

            // Start real-time listener thread (Unit 6)
            listenerThread = new ClientListener(socket, in);
            new Thread(listenerThread).start();

            JOptionPane.showMessageDialog(this, "Connected to server successfully!");

            openDataViewerFrame();   // open main view

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cannot connect to server.\nStart ServerMain first!", "Error", JOptionPane.ERROR_MESSAGE);
        }
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