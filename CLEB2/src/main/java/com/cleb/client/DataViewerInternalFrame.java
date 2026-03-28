package com.cleb.client;

import com.cleb.dao.ReservationDAO;
import com.cleb.model.Reservation;
import com.cleb.server.ServiceFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DataViewerInternalFrame extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton btnRefresh;
    private JButton btnClose;
    private GridBagConstraints gbc;

    public DataViewerInternalFrame() {
        super("Live Data from Database - Pending Reservations", true, true, true, true);
        setSize(950, 650);
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        // Title Label
        JLabel lblTitle = new JLabel("Pending Reservations (Live from MySQL)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblTitle, gbc);

        // Create the JTable
        String[] columns = {"Reservation ID", "Booked By", "Start Time", "End Time", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Refresh Button
        btnRefresh = new JButton("REFRESH FROM DATABASE");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnRefresh, gbc);

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLiveData();
            }
        });

        // Close Button
        btnClose = new JButton("CLOSE WINDOW");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnClose, gbc);

        btnClose.addActionListener(e -> dispose());

        // Load data when the window opens
        loadLiveData();
    }

    private void loadLiveData() {
        // Clear old rows
        model.setRowCount(0);

        // Get real data from database using DAO
        ReservationDAO resDAO = ServiceFactory.getReservationDAO();
        List<Reservation> pending = resDAO.getPendingReservations();

        for (Reservation r : pending) {
            model.addRow(new Object[]{
                r.getReservationId(),
                r.getBookedBy() != null ? r.getBookedBy().getUsername() : "Unknown",
                r.getStartTime(),
                r.getEndTime(),
                r.getStatus()
            });
        }

        if (pending.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No pending reservations found in database", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Loaded " + pending.size() + " pending reservations from MySQL", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}