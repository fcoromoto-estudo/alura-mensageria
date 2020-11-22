package br.com.fcoromoto.estudo.alura.mensageria.jms.teste.topico;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.topico.ConsumidorTopico;
import br.com.fcoromoto.estudo.alura.mensageria.jms.modelo.Pedido;
import lombok.extern.slf4j.Slf4j;

import javax.jms.ObjectMessage;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
public class TesteConsumidorPedidoTopico {

    public static void main(String[] args) throws Exception {
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
        String sessionID = Objects.requireNonNull(args[0]);
        consumirMensagemFiltrada(sessionID);

    }


    private static void consumirMensagemFiltrada(String sessionID) throws Exception {

        try (ConsumidorTopico consumidor = ConsumidorTopico.init(sessionID)) {

            consumidor.setMessageListener(message -> {
                try {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    Pedido pedido = (Pedido) objectMessage.getObject();

                    log.info("Recebendo pedido: " + pedido);
                } catch (Exception e) {
                    log.error("", e);
                }
            });


            new Scanner(System.in).nextLine();
        }
    }
}
