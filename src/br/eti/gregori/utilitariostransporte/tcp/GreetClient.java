package br.eti.gregori.utilitariostransporte.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GreetClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        GreetClient client = new GreetClient();
        try {
            client.startConnection("127.0.0.1", 12345);
            String response = client.sendMessage("hello server");
            System.out.println(response);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            client.stopConnection();
        }
    }
}
