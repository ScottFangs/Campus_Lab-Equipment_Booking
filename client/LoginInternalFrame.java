package com.cleb.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginInternalFrame extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRole;

    public LoginInternalFrame() {
        super("Login", true, true, true, true);
        setSize(400, 250);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Username:"));
        txtUsername = new JTextField("demario");
        add(txtUsername);

        add(new JLabel("Password:"));
        txtPassword = new JPasswordField("pass123");
        add(txtPassword);

        add(new JLabel("Role:"));
        cmbRole = new JComboBox<>(new String[]{"STUDENT", "TECHNICIAN", "ADMIN"});
        add(cmbRole);

        JButton btnLogin = new JButton("Login (Demo)");
        btnLogin.setActionCommand("LOGIN_DEMO");
        btnLogin.addActionListener(this);
        add(btnLogin);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setActionCommand("CANCEL");
        btnCancel.addActionListener(this);
        add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("LOGIN_DEMO".equals(command)) {
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());
            String role = (String) cmbRole.getSelectedItem();

            JOptionPane.showMessageDialog(this,
                "Demo Login Successful!\nUsername: " + user + "\nRole: " + role);
            System.out.println("Login demo - role = " + role);
        } 
        else if ("CANCEL".equals(command)) {
            dispose();   // close the window
        }
    }
    
    
    
    
}