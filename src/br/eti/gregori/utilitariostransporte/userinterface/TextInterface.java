package br.eti.gregori.utilitariostransporte.userinterface;

import br.eti.gregori.utilitariostransporte.ipresolution.IPAddress;
import br.eti.gregori.utilitariostransporte.tcp.TCPClient;
import br.eti.gregori.utilitariostransporte.tcp.TCPServer;
import br.eti.gregori.utilitariostransporte.udp.UDPClient;
import br.eti.gregori.utilitariostransporte.udp.UDPServer;

import java.util.Scanner;

public class TextInterface {
    private final Scanner reader;

    public TextInterface() {
        reader = new Scanner(System.in);
        reader.useDelimiter("\\n");
    }

    public boolean run() {
        boolean exit = false;

        String option = menu(); // obtém a opção recebida do menu

        switch(option) {
            case "1":
                // EnderecosIP
                System.out.println("Endereços IP");
                IPAddress.run();
                break;
            case "2":
                // TCPServer
                System.out.println("Servidor TCP - abra em outra janela um cliente TCP");
                TCPServer.run();
                break;
            case "3":
                // TCPClient
                System.out.println("Cliente TCP");
                TCPClient.run();
                break;
            case "4":
                // UDPServer
                System.out.println("Servidor UDP - Abra em outra janela umc liente UDP");
                UDPServer.run();
                break;
            case "5":
                // UDPClient
                System.out.println("Cliente UDP");
                UDPClient.run();
                break;
            case "s":
                exit = true;
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        return exit;
    }

    private String menu() {
        System.out.println("-------------------------------------");
        System.out.println("Utilitários para Camada de Transporte");
        System.out.println();
        System.out.println("1 - Endereços IP");
        System.out.println("2 - Servidor TCP");
        System.out.println("3 - Cliente TCP");
        System.out.println("4 - Servidor UDP");
        System.out.println("5 - Cliente UDP");
        System.out.println("S - Sair");
        System.out.println();

        System.out.print("Escolha: ");

        return reader.next().toLowerCase();
    }
}
