package br.com.fcoromoto.estudo.alura.mensageria.jms.teste.fila;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.fila.ConsumidorFila;
import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.fila.ConsumidorFilaSession;
import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.fila.ProdutorFila;
import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Scanner;

@Slf4j
public class TesteConsumidorFilaRollback {

    public static void main(String[] args) {
        enviarMensagem();
        consumirMensagem();
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void enviarMensagem() {
        try (ProdutorFila produtor = ProdutorFila.init()) {
            produtor.enviarMensagem("Enviando mensagem para rollback ");
            sleep();
        } catch (Exception e) {
            log.error("erro ao enviar mensagem", e);
        }
    }

    private static void consumirMensagem() {

        try (ConsumidorFila consumidor = ConsumidorFilaSession.initWithSession()) {

            consumidor.setMessageListener(message -> {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    log.info("Recebendo msg: " + textMessage.getText());
                    consumidor.rollback();
                } catch (JMSException e) {
                    log.error("", e);
                    consumidor.rollback();
                }
            });

            new Scanner(System.in).nextLine();
        } catch (Exception e) {
            log.error("erro ao ler mensagens", e);
        }
    }

}
