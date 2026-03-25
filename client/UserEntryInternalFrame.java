package com.cleb.client;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserEntryInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	public UserEntryInternalFrame() {
        super("Enter New User", true, true, true, true);
        setSize(500, 300);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Username:"));
        JTextField txtUsername = new JTextField("Demario Scott");
        add(txtUsername);

        add(new JLabel("Password:"));
        JPasswordField txtPassword = new JPasswordField("2106675@utech!");
        add(txtPassword);

        add(new JLabel("Role:"));
        JComboBox<String> cmbRole = new JComboBox<>(new String[]{"STUDENT", "TECHNICIAN", "ADMIN"});
        add(cmbRole);

        JButton btnAdd = new JButton("Add User (Demo)");
        btnAdd.addActionListener(e -> {
            System.out.println("Would create User: " + txtUsername.getText() + " as " + cmbRole.getSelectedItem());
            JOptionPane.showMessageDialog(this, "User data entered (demo only)");
        });
        add(btnAdd);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
    }
}