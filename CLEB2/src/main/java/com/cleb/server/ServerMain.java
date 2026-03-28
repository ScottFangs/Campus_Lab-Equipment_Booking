package com.cleb.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerMain {

    private static final Logger logger = LogManager.getLogger(ServerMain.class);
    private static final int PORT = 5000;
    private static final int MAX_CLIENTS = 30;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_CLIENTS);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("CLEB Server started successfully on port " + PORT);
            logger.info("Waiting for clients...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("New client connected from " + clientSocket.getInetAddress());

                // Unit 6 + Unit 7: Hand off to a thread
                threadPool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            logger.error("Server crashed", e);
        }
    }
}