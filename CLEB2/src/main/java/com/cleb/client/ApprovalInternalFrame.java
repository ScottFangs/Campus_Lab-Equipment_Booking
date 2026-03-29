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

public class ApprovalInternalFrame extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton btnApprove;
    private JButton btnReject;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnClose;
    private GridBagConstraints gbc;

    public ApprovalInternalFrame() {
        super("Approve / Reject / Delete Reservations (Admin)", true, true, true, true);
        setSize(1000, 650);
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        JLabel lblTitle = new JLabel("Pending Reservations - Admin View");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblTitle, gbc);

        String[] columns = {"Reservation ID", "Booked By", "Lab", "Type", "Start Time", "End Time", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);

        btnApprove = new JButton("APPROVE SELECTED");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnApprove, gbc);

        btnApprove.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            long id = (Long) model.getValueAt(row, 0);
            ServiceFactory.getReservationDAO().approveReservation(id);
            JOptionPane.showMessageDialog(this, "Approved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadPendingReservations();   // refresh but keep all rows
        });

        btnReject = new JButton("REJECT SELECTED");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnReject, gbc);

        btnReject.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            long id = (Long) model.getValueAt(row, 0);
            ServiceFactory.getReservationDAO().rejectReservation(id);
            JOptionPane.showMessageDialog(this, "Rejected successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadPendingReservations();
        });

        btnDelete = new JButton("DELETE SELECTED");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnDelete, gbc);

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            long id = (Long) model.getValueAt(row, 0);

            int confirm = JOptionPane.showConfirmDialog(this, "Delete this reservation permanently?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Delete from database (you can add delete method to DAO later)
                model.removeRow(row);
                JOptionPane.showMessageDialog(this, "Row deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnRefresh = new JButton("REFRESH");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnRefresh, gbc);

        btnRefresh.addActionListener(e -> loadPendingReservations());

        btnClose = new JButton("CLOSE");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnClose, gbc);

        btnClose.addActionListener(e -> dispose());

        loadPendingReservations();
    }

    private void loadPendingReservations() {
        model.setRowCount(0);
        ReservationDAO dao = ServiceFactory.getReservationDAO();
        List<Reservation> list = dao.getPendingReservations();

        for (Reservation r : list) {
            String labName = "Unknown Lab";
            String type = "Unknown";

            if (r.getBookedSeat() != null && r.getBookedSeat().getLab() != null) {
                labName = r.getBookedSeat().getLab().getName();
                type = "Seat";
            } else if (r.getBookedEquipment() != null && r.getBookedEquipment().getLab() != null) {
                labName = r.getBookedEquipment().getLab().getName();
                type = "Equipment";
            }

            model.addRow(new Object[]{
                r.getReservationId(),
                r.getBookedBy() != null ? r.getBookedBy().getUsername() : "Unknown",
                labName,
                type,
                r.getStartTime(),
                r.getEndTime(),
                r.getStatus()
            });
        }
    }
}