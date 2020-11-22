package br.com.fcoromoto.estudo.alura.mensageria.jms.teste;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.Consumidor;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import java.util.Scanner;

public class TesteConsumidor {

    public static void main(String[] args) throws Exception {

        lendoMensagens();
        new Scanner(System.in).nextLine();
    }

    private static void lendoMensagens() throws Exception {
        try (Consumidor consumidor = Consumidor.init()) {

            MessageConsumer consumer = consumidor.getConsumer();

            consumer.setMessageListener(message -> {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("Recebendo msg: " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
