package br.com.fcoromoto.estudo.alura.mensageria.jms.teste.fila;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.fila.ConsumidorFila;
import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Scanner;

@Slf4j
public class TesteConsumidorFila {

    public static void main(String[] args) throws Exception {

        try (ConsumidorFila consumidor = ConsumidorFila.init()) {

            consumidor.setMessageListener(message -> {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    log.info("Recebendo msg: " + textMessage.getText());
                } catch (JMSException e) {
                    log.error("", e);
                }
            });

            new Scanner(System.in).nextLine();
        }
    }

}
