package br.eti.gregori.utilitariostransporte.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void run() {
        try {
            // preparamos um canal de comunicação para enviar o datagrama
            DatagramSocket socket = new DatagramSocket();

            // Informações do servidor e porta
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            int serverPort = 12345;
            byte[] num = { 12 }; // array de bytes para envio de informação
            // datagramPacket que enviará para o servidor e porta, o número criado
            DatagramPacket datagram = new DatagramPacket (num, 1, serverAddress, serverPort);

            socket.send(datagram);
            System.out.println ("Datagrama UDP enviado com sucesso.");
        } catch (IOException exc) {
            System.err.println (exc.toString());
        }
    }
}
