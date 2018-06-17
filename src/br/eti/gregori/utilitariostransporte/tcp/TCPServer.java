package br.eti.gregori.utilitariostransporte.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPServer {
    public static void run() {
        try {
            // Abre um socket TCP e escuta na porta 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            Socket socket = serverSocket.accept();

            // cria um "stream" de dados para ser enviado via rede
            // através da classe DataOutputStream, que é obtida do socket TCP
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            SocketAddress s = socket.getRemoteSocketAddress();
            System.out.println("Enviando número ao cliente: " + s.toString());
            outputStream.writeInt(12); // envia o número 12 para o cliente

            socket.close(); // fecha a conexão ao fim dos trabalhos
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

    }
}
