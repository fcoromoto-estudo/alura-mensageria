package br.com.fcoromoto.estudo.alura.mensageria.jms.teste.fila;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.fila.ConsumidorFila;
import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Scanner;

@Slf4j
public class TesteConsumidorDLQ {

    public static void main(String[] args) {
        consumirMensagem();
    }

    private static void consumirMensagem() {

        try (ConsumidorFila consumidor = ConsumidorFila.init("DLQ")) {

            consumidor.setMessageListener(message -> {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    log.info("Recebendo msg: " + textMessage.getText());
                } catch (JMSException e) {
                    log.error("", e);
                }
            });

            new Scanner(System.in).nextLine();
        } catch (Exception e) {
            log.error("erro ao ler mensagens", e);
        }
    }

}
