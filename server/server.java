package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        int portNumber = 12345;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Serveur démarré. En attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouvelle connexion entrante : " + clientSocket.getInetAddress());
                
                // Créer un nouveau thread pour gérer la connexion cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du démarrage du serveur : " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Message reçu du client : " + inputLine);

                // Répondre différemment en fonction du message reçu du client
                String response;
                if (inputLine.equalsIgnoreCase("bonjour")) {
                    response = "Bonjour, client !";
                } else if (inputLine.equalsIgnoreCase("comment ça va ?")) {
                    response = "Je vais bien, merci ! Et vous ?";
                } else {
                    response = "Message non reconnu";
                }

                // Envoyer la réponse au client
                out.println(response);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la communication avec le client : " + e.getMessage());
        }
    }
}
