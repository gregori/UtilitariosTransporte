package br.eti.gregori.utilitariostransporte;

import br.eti.gregori.utilitariostransporte.userinterface.TextInterface;

public class Main {
    public static void main(String[] args) {
        TextInterface textInterface = new TextInterface();

        boolean exit;

        do {
            exit = textInterface.run();
        } while (!exit);
    }
}
