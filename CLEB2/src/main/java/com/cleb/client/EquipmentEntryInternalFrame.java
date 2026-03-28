package com.cleb.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipmentEntryInternalFrame extends JInternalFrame {

    private JTextField txtId;
    private JTextField txtDesc;
    private JComboBox<String> cmbStatus;
    private JButton btnAdd;
    private JButton btnCancel;
    private GridBagConstraints gbc;

    public EquipmentEntryInternalFrame() {
        super("Enter New Equipment", true, true, true, true);
        setSize(520, 340);
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        // Equipment ID
        JLabel lblId = new JLabel("Equipment ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblId, gbc);

        txtId = new JTextField("EQ-3DP-0007", 25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtId, gbc);

        // Description
        JLabel lblDesc = new JLabel("Description:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblDesc, gbc);

        txtDesc = new JTextField("3D printer (metal)", 25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtDesc, gbc);

        // Status
        JLabel lblStatus = new JLabel("Status:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblStatus, gbc);

        cmbStatus = new JComboBox<>(new String[]{"AVAILABLE", "MAINTENANCE", "BOOKED"});
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(cmbStatus, gbc);

        // Add Button
        btnAdd = new JButton("ADD EQUIPMENT");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAdd, gbc);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText().trim();
                String desc = txtDesc.getText().trim();

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(EquipmentEntryInternalFrame.this, "Please enter Equipment ID", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (desc.isEmpty()) {
                    JOptionPane.showMessageDialog(EquipmentEntryInternalFrame.this, "Please enter Description", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(EquipmentEntryInternalFrame.this, "Equipment added successfully (Demo)\nID: " + id, "Success", JOptionPane.INFORMATION_MESSAGE);
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