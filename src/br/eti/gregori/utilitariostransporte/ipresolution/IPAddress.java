package br.eti.gregori.utilitariostransporte.ipresolution;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddress {
    public static void run() {
        // Endereço IP na forma de array de bytes
        byte address1[] = { (byte)177, (byte)52, (byte)222, (byte)11 };

        try
        {
            System.out.print ("Endereço 1: ");
            // Imprime o endereço na notação de pontos, com um for
            // Note que alguns valores aparecerão negativos, devido à extensão do tipo byte
            for (int num=0; num < address1.length; num++)
            {
                if (num == 3)
                    System.out.println(address1[num]);
                else
                    System.out.print(address1[num] + ".");
            }

            // obtem o "nome" do endereço, através da classe InetAddress
            InetAddress address2 = InetAddress.getByAddress(address1);
            System.out.println("Endereço 2: " + address2.getHostAddress());
            System.out.println("Nome DNS do endereço 2: " + address2.getHostName());

            // cria um endereço a partir de um nome
            // imprime seu IP e seu nome
            InetAddress address3 = InetAddress.getByName("www.google.com");
            System.out.println("Endereço 3: " + address3.getHostAddress());
            System.out.println("Nome DNS do endereço 3: " + address3.getHostName());
        }
        catch (UnknownHostException exc)
        {
            System.err.println(exc.getMessage());
        }
    }
}
