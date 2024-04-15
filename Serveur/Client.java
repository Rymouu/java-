package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int port = 12345;
        try {
            Socket socket = new Socket("localhost", 1234);
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Bonjour, serveur!");
            String response = in.readLine();
            System.out.println("Réponse du serveur : " + response);

            socket.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du client : " + e.getMessage());
        }
    }
}
