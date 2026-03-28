package com.cleb.client;

import com.cleb.common.Response;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.SwingUtilities;

public class ClientListener implements Runnable {

    private final Socket socket;
    private final ObjectInputStream in;

    public ClientListener(Socket socket, ObjectInputStream in) {
        this.socket = socket;
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Response response = (Response) in.readObject();

                // Update GUI safely (Unit 4)
                SwingUtilities.invokeLater(() -> {
                    System.out.println("Real-time update received: " + response.getStatus());
                    // TODO: Later we will refresh JTables here
                });
            }
        } catch (Exception e) {
            // Client disconnected or server closed
        }
    }
}