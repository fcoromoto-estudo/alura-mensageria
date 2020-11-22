package br.com.fcoromoto.estudo.alura.mensageria.jms.teste.topico;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.topico.ConsumidorTopico;
import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
public class TesteConsumidorTopico {

    public static void main(String[] args) throws Exception {

        String sessionID = Objects.requireNonNull(args[0]);
        boolean isParametroFiltroPresente = isPresent(args, 1);

        if (isParametroFiltroPresente) {
            boolean mensagemFiltrada = Boolean.parseBoolean(args[1]);
            consumirMensagemFiltrada(sessionID, true, mensagemFiltrada);
        } else {
            consumirMensagemFiltrada(sessionID, false, false);
        }
    }

    public static boolean isPresent(String[] args, int indice) {
        try {
            return args[indice] != null;
        } catch (Exception e) {
            return false;
        }
    }

    private static void consumirMensagemFiltrada(String sessionID, boolean usarSeletor, boolean mensagemFiltrada)
            throws Exception {

        String selector = usarSeletor ? ("filtro = " + mensagemFiltrada) : null;

        try (ConsumidorTopico consumidor = ConsumidorTopico.init(sessionID, selector)) {

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
