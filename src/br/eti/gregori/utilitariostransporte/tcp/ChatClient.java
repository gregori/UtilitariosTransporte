package br.eti.gregori.utilitariostransporte.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;

    public void start(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        scanner = new Scanner(System.in);
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
                ChatClient.this.stop();
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
                    System.out.println("Servidor: " + msg);
                }

                System.out.println("Servidor desconectou.");

                ChatClient.this.stop();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient();
        try {
            client.start("127.0.0.1", 12345);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            client.stop();
        }
    }

}
