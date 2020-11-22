package br.com.fcoromoto.estudo.alura.mensageria.jms.atores;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

public class Produtor extends MensageriaBase {

    private MessageProducer produtor;

    private Produtor() {}

    public static Produtor init() throws Exception {
        Produtor consumidor = new Produtor();
        consumidor.createConsumer();
        return consumidor;
    }

    private void createConsumer() throws Exception {
        super.startConnection();
        produtor = getSession().createProducer(getQueue());
    }

    public MessageProducer getProdutor() {
        return produtor;
    }

    public void enviarMensagem(String mensagem) {
        try {
            TextMessage textMessage = getSession().createTextMessage(mensagem);
            produtor.send(textMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close() {
        super.close();
        try {
            getSession().close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
