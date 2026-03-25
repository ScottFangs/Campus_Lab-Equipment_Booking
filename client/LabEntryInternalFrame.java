package com.cleb.client;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LabEntryInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	public LabEntryInternalFrame() {
        super("Enter New Lab", true, true, true, true);
        setSize(500, 300);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Lab Name:"));
        JTextField txtName = new JTextField("SCIT Software Engineering Lab");
        add(txtName);

        add(new JLabel("Total Seats:"));
        JTextField txtSeats = new JTextField("40");
        add(txtSeats);

        add(new JLabel("Campus:"));
        JTextField txtCampus = new JTextField("Papine Campus");
        add(txtCampus);

        JButton btnAdd = new JButton("Add Lab (Demo)");
        btnAdd.addActionListener(e -> {
            System.out.println("Would create Lab: " + txtName.getText() + " (" + txtSeats.getText() + " seats)");
            JOptionPane.showMessageDialog(this, "Lab data entered (demo only)");
        });
        add(btnAdd);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());
        add(btnClose);
    }
}