package br.eti.gregori.utilitariostransporte.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        scanner = new Scanner(System.in);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        new ChatWriter().start();
        new ChatReceiver().start();
    }

    private class ChatWriter extends Thread {
        String msg;

        @Override
        public void run() {
            while((msg = scanner.nextLine()) != null) {
                if ("!quit".equals(msg)) {
                    out.println("Saindo do chat.");
                    break;
                }
                out.println(msg);
            }
            try {
                ChatServer.this.stop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class ChatReceiver extends Thread {
        String msg;

        @Override
        public void run() {
            try {
                while ((msg = in.readLine()) != null) {
                    System.out.println("Cliente: " + msg);
                }

                System.out.println("Cliente desconectou.");

                ChatServer.this.stop();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer();
        try {
            server.start(12345);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }

}
