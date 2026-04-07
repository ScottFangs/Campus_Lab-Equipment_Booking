package com.cleb.server;

import com.cleb.common.Request;
import com.cleb.common.Response;
import com.cleb.dao.ReservationDAO;
import com.cleb.model.Reservation;
import com.cleb.model.User;
import com.cleb.server.ServiceFactory;
import com.cleb.dao.UserDAO;

import java.io.*;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(ClientHandler.class);

    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private User loggedInUser;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            logger.info("ClientHandler thread started for " + socket.getInetAddress());

            while (true) {
                Request request = (Request) in.readObject();
                logger.info("Received action: " + request.getAction());

                Response response = processRequest(request);

                out.writeObject(response);
                out.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.info("Client disconnected: " + socket.getInetAddress());
        }
    }

    private Response processRequest(Request request) {
        String action = request.getAction();
        Object payload = request.getPayload();

        if ("LOGIN".equals(action)) {
            String[] credentials = (String[]) payload; // [username, password]
            UserDAO userDAO = ServiceFactory.getUserDAO();
            User user = userDAO.authenticate(credentials[0], credentials[1]);

            if (user != null) {
                loggedInUser = user;
                return new Response(request.getCorrelationId(), "SUCCESS", user);
            } else {
                return new Response(request.getCorrelationId(), "FAIL", "Invalid credentials");
            }
        }

        if ("BOOK".equals(action)) {
            ReservationDAO dao = ServiceFactory.getReservationDAO();
            Reservation res = (Reservation) payload;
            dao.createReservation(res);
            return new Response(request.getCorrelationId(), "SUCCESS", "Booking created");
        }

        return new Response(request.getCorrelationId(), "ERROR", "Unknown action");
    }
}
