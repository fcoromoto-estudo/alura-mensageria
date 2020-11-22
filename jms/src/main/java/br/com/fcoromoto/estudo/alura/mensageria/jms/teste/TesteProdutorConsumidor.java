package br.com.fcoromoto.estudo.alura.mensageria.jms.teste;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.Consumidor;
import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.Produtor;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import java.util.Scanner;

public class TesteProdutorConsumidor {

    public static void main(String[] args) throws Exception {
        Produtor produtor = Produtor.init().init();

        MessageConsumer joao = criarConsumidor("João");
        MessageConsumer pedro = criarConsumidor("Pedro");

        produtor.enviarMensagem("Teste 1");
        produtor.enviarMensagem("Teste 2");

        new Scanner(System.in).nextLine();
        joao.close();
        pedro.close();
    }

    private static MessageConsumer criarConsumidor(String idConsumidor) throws Exception {
        MessageConsumer consumer = Consumidor.init().getConsumer();

        consumer.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                System.out.println(idConsumidor + " Recebendo msg: "+ textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        return consumer;
    }
}
