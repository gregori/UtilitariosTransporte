package br.eti.gregori.utilitariostransporte.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    public static void run() {
        try  {
            // Cria um socket que vai conectar ao servidor 127.0.0.1 e porta 12345
            Socket socket = new Socket("127.0.0.1", 12345);

            // obtém um inputStream, que vai receber uma mensagem do servidor, ao completar a conexão
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            System.out.println("Número recebido: " + inputStream.readInt());

            socket.close(); // encerra a conexão
        } catch (IOException exc) {
            System.out.println(exc.toString());
        }
    }
}
