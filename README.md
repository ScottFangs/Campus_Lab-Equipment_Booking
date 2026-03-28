# Campus_Lab-Equipment_Booking
#Advance Programming 2025/2026 Semester 2 Group Project. Real Time Dispatch for Utech Campus Lab
#Group Members: Demario Scott (2106675), Jadon Johnson (2007278), Abigail Bembridge (2305624), Giomar Griffiths (2307401).


+ Purpose & Context
You are required to work in groups of four (4) members. Your group must design and
implement a Java‐based Client/Server software system that enables students and staff to
book and manage laboratory seats and equipment used across the Faculty of Engineering
and Computing (FENC) at the University of Technology, Jamaica (UTech, Ja.). This includes
labs within the School of Computing & Information Technology (SCIT) and the School of
Engineering (SOE). A lab seat refers to a specific workstation or seating position inside a
laboratory that a student can reserve for a given time slot. It is like booking a workstation in
a library computer lab.


+How to current get all the files onto your computer (as of March 28th 2026):
- Download all the files in UPDATED CLEB 2


+How to Run the Project:
- Start the Database
Open USBWebserver → Start MySQL (port 3307)
Open phpMyAdmin (localhost/phpmyadmin)
Make sure database cleb_db exists with the tables and sample users.

- Start the Server
Run ServerMain.java first (it must be running before clients connect).

- Start the Client
Run ClientMain.java
Login window will appear automatically.
Use these accounts:
Student: demario / pass1
Admin: admin1 / admin123
Technician: tech1 / tech123

- Test Real-Time
Login with two different clients.
Use the Admin account to approve/reject a reservation.
The change should appear instantly in the other client’s Live Data Viewer.

