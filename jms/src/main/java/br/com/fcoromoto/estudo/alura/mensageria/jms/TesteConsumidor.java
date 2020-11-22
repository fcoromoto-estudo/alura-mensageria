package br.com.fcoromoto.estudo.alura.mensageria.jms;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import java.util.Scanner;

public class TesteConsumidor {

    public static void main(String[] args) throws Exception {

        try (Consumidor consumidor = Consumidor.init()) {

            MessageConsumer consumer = consumidor.getConsumer();

            consumer.setMessageListener(message -> {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("Recebendo msg: "+ textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });


            new Scanner(System.in).nextLine();
        }
    }
}
