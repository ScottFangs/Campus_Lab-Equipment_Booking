package com.cleb.model;

//import java.util.List;

public class ModelTest {
    public static void main(String[] args) {
        // Sample data from PDF pages 1-2
        Lab lab1 = new Lab(1, "SCIT Software Engineering Lab", 40, "Papine Campus");
        Lab lab2 = new Lab(2, "SOE Industrial & Mechanical Engineering Lab", 24, "Papine Campus");
        
        Equipment printer = new Equipment("EQ-3DP-0007", "3D printer (metal)", "AVAILABLE", lab2);
        lab2.addEquipment(printer);
        
        Student student = new Student(1001, "demario", "pass123");
        
        System.out.println("=== CLEB Sample Data Loaded ===");
        System.out.println("Lab: " + lab1.getName() + " (" + lab1.getTotalSeats() + " seats)");
        System.out.println("Equipment: " + printer.getEquipmentId() + " - " + printer.getStatus());
        System.out.println("Student: " + student);
        
        // for-each example (Unit 1)
        for (Seat s : lab1.getSeats()) {
            if (s.getSeatNumber() <= 3) System.out.println("Seat " + s.getSeatNumber());
        }
    }
}