package com.cleb.client;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cleb.model.Equipment;
import com.cleb.model.Lab;

public class DataViewerInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	
    public DataViewerInternalFrame() {
        super("View Sample Data from PDF", true, true, true, true);
        setSize(950, 650);
        setLayout(new BorderLayout());

        // Create sample data exactly as in the project brief
        Lab lab1 = new Lab(1, "SCIT Software Engineering Lab", 40, "Papine Campus");
        Lab lab2 = new Lab(2, "SOE Industrial & Mechanical Engineering Lab", 24, "Papine Campus");

        Equipment printer = new Equipment("EQ-3DP-0007", "3D printer (metal)", "AVAILABLE", lab2);
        lab2.addEquipment(printer);

        // Build the JTable
        String[] columns = {"Lab ID", "Lab Name", "Total Seats", "Campus", "Equipment Example"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Add rows using your model classes
        model.addRow(new Object[]{
            lab1.getLabId(), lab1.getName(), lab1.getTotalSeats(), lab1.getCampus(), "—"
        });
        model.addRow(new Object[]{
            lab2.getLabId(), lab2.getName(), lab2.getTotalSeats(), lab2.getCampus(), printer.getEquipmentId()
        });

        JTable table = new JTable(model);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // Demo button at the bottom
        JButton btnRefresh = new JButton("Refresh Sample Data (Demo)");
        btnRefresh.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Sample data loaded from project brief (PDF pages 1-2)"));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnRefresh);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
