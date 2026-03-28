package com.cleb.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabEntryInternalFrame extends JInternalFrame {

    private JTextField txtName;
    private JTextField txtSeats;
    private JTextField txtCampus;
    private JButton btnAdd;
    private JButton btnCancel;
    private GridBagConstraints gbc;

    public LabEntryInternalFrame() {
        super("Enter New Lab", true, true, true, true);
        setSize(520, 340);
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        // Lab Name
        JLabel lblName = new JLabel("Lab Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblName, gbc);

        txtName = new JTextField("SCIT Software Engineering Lab", 25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtName, gbc);

        // Total Seats
        JLabel lblSeats = new JLabel("Total Seats:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblSeats, gbc);

        txtSeats = new JTextField("40", 25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtSeats, gbc);

        // Campus
        JLabel lblCampus = new JLabel("Campus:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblCampus, gbc);

        txtCampus = new JTextField("Papine Campus", 25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtCampus, gbc);

        // Add Button
        btnAdd = new JButton("ADD LAB");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAdd, gbc);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText().trim();
                String seats = txtSeats.getText().trim();
                String campus = txtCampus.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(LabEntryInternalFrame.this, "Please enter Lab Name", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (seats.isEmpty()) {
                    JOptionPane.showMessageDialog(LabEntryInternalFrame.this, "Please enter Total Seats", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (campus.isEmpty()) {
                    JOptionPane.showMessageDialog(LabEntryInternalFrame.this, "Please enter Campus", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(LabEntryInternalFrame.this, "Lab added successfully (Demo)\n" + name + " (" + seats + " seats)", "Success", JOptionPane.INFORMATION_MESSAGE);
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