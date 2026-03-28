package com.cleb.client;

import com.cleb.model.Role;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEntryInternalFrame extends JInternalFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<Role> cmbRole;
    private JButton btnAdd;
    private JButton btnCancel;
    private GridBagConstraints gbc;

    public UserEntryInternalFrame() {
        super("Enter New User", true, true, true, true);
        setSize(520, 340);
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        // Username
        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblUsername, gbc);

        txtUsername = new JTextField("demario", 25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtUsername, gbc);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField("pass123", 25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtPassword, gbc);

        // Role
        JLabel lblRole = new JLabel("Role:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblRole, gbc);

        cmbRole = new JComboBox<>(Role.values());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(cmbRole, gbc);

        // Add Button
        btnAdd = new JButton("ADD USER");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAdd, gbc);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(UserEntryInternalFrame.this, "Please enter Username", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(UserEntryInternalFrame.this, "Please enter Password", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(UserEntryInternalFrame.this, "User added successfully (Demo)\nUsername: " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        // Cancel Button
        btnCancel = new JButton("CANCEL");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnCancel, gbc);

        btnCancel.addActionListener(e -> dispose());
    }
}