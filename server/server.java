package server;

// Server.java (dans le package server)

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 1234;
    public Server() {
        int p = PORT;
    }
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveur démarré. En attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouvelle connexion: " + clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
