// Client.java (dans le package client)
package Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static final String SERVER_IP = "127.0.0.1"; // Adresse IP du serveur
    public static final int SERVER_PORT = 1234; // Port du serveur

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connecté au serveur.");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                out.println(userInputLine); // Envoyer les données au serveur

                String serverResponse = in.readLine(); // Recevoir la réponse du serveur
                System.out.println("Réponse du serveur : " + serverResponse);
            }

            userInput.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
