package br.com.fcoromoto.estudo.alura.mensageria.jms.teste;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.Produtor;

import java.util.Scanner;

public class TesteProdutor {

    public static void main(String[] args) throws Exception {
        Produtor produtor = Produtor.init().init();

        for (int i = 1; i <= 100; i++) {
            produtor.enviarMensagem("Teste " + i);
        }

        new Scanner(System.in).nextLine();

        produtor.close();
    }
}
