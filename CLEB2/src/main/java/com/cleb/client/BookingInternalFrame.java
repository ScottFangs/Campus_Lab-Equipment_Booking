package com.cleb.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.cleb.dao.ReservationDAO;
import com.cleb.model.Reservation;
import com.cleb.model.User;
import com.cleb.server.ServiceFactory;


public class BookingInternalFrame extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cmbType;
    private JComboBox<String> cmbLab;
    private JTextField txtDate;
    private JTextField txtStartTime;
    private JTextField txtEndTime;
    private JButton btnSubmit;
    private JButton btnClose;
    private GridBagConstraints gbc;

    public BookingInternalFrame() {
        super("Create New Reservation (Student)", true, true, true, true);
        setSize(580, 420);
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        JLabel lblType = new JLabel("Reservation Type:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblType, gbc);

        cmbType = new JComboBox<>(new String[]{"Lab Seat", "Equipment"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(cmbType, gbc);

        JLabel lblLab = new JLabel("Select Lab:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblLab, gbc);

        cmbLab = new JComboBox<>(new String[]{"SCIT Software Engineering Lab", "SCIT Networking & Systems Lab", "SOE Industrial & Mechanical Engineering Lab"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(cmbLab, gbc);

        JLabel lblDate = new JLabel("Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblDate, gbc);

        txtDate = new JTextField("2026-04-10", 25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtDate, gbc);

        JLabel lblStart = new JLabel("Start Time (HH:mm):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblStart, gbc);

        txtStartTime = new JTextField("09:00", 25);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtStartTime, gbc);

        JLabel lblEnd = new JLabel("End Time (HH:mm):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblEnd, gbc);

        txtEndTime = new JTextField("11:00", 25);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtEndTime, gbc);

        btnSubmit = new JButton("SUBMIT RESERVATION");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSubmit, gbc);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User currentUser = Session.getLoggedInUser();
                if (currentUser == null || currentUser.getRole() != com.cleb.model.Role.STUDENT) {
                    JOptionPane.showMessageDialog(BookingInternalFrame.this, "Only students can book", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Simple double-booking check (basic version)
                String date = txtDate.getText().trim();
                String start = txtStartTime.getText().trim();
                String end = txtEndTime.getText().trim();

                if (date.isEmpty() || start.isEmpty() || end.isEmpty()) {
                    JOptionPane.showMessageDialog(BookingInternalFrame.this, "Please fill date and time", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ReservationDAO dao = ServiceFactory.getReservationDAO();
                Reservation res = new Reservation();
                res.setBookedBy(currentUser);
                res.setStartTime(date + " " + start);
                res.setEndTime(date + " " + end);
                res.setStatus("PENDING");

                dao.createReservation(res);

                JOptionPane.showMessageDialog(BookingInternalFrame.this, 
                    "Reservation submitted successfully!\nWaiting for admin approval.", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        btnClose = new JButton("CLOSE");
        gbc.gridy = 6;
        add(btnClose, gbc);

        btnClose.addActionListener(e -> dispose());
    }
}