package com.cleb.client;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EquipmentEntryInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	
	
	public EquipmentEntryInternalFrame() {
        super("Enter New Equipment", true, true, true, true);
        setSize(500, 300);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Equipment ID:"));
        JTextField txtId = new JTextField("EQ-3DP-0007");
        add(txtId);

        add(new JLabel("Description:"));
        JTextField txtDesc = new JTextField("3D printer (metal)");
        add(txtDesc);

        add(new JLabel("Status:"));
        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"AVAILABLE", "MAINTENANCE", "BOOKED"});
        add(cmbStatus);

        JButton btnAdd = new JButton("Add Equipment (Demo)");
        btnAdd.addActionListener(e -> {
            System.out.println("Would create Equipment: " + txtId.getText());
            JOptionPane.showMessageDialog(this, "Equipment data entered (demo only)");
        });
        add(btnAdd);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
    }
	
	
}
