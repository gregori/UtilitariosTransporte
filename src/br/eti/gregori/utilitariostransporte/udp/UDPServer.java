package br.eti.gregori.utilitariostransporte.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void run() {
        try {
            // Cria um datagrama UDP, que vai receber segmentos UPD
            // na porta 12345
            DatagramSocket socket = new DatagramSocket(12345);


            byte[] num = new byte[1];
            // Constrói um DatagramPacket para receber datagramas de tamanho 1 byte
            DatagramPacket datagram = new DatagramPacket(num, 1); // e os aloca no array "num"
            socket.receive(datagram); // recebe o datagrama
            //byte[] rcvData = datagram.getData();

            System.out.println ("O número recebido foi: " + num[0]);
        } catch (IOException exc) {
            System.err.println(exc.toString());
        }
    }
}
