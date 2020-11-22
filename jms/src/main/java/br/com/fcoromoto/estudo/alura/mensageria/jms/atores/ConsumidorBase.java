package br.com.fcoromoto.estudo.alura.mensageria.jms.atores;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

public abstract class ConsumidorBase extends MensageriaBase {

    private MessageConsumer consumer;

    public void startConsumidor() throws Exception {
        startConnection();
        consumer = createConsumer();
    }

    protected abstract MessageConsumer createConsumer() throws Exception;

    public void setMessageListener(MessageListener listener) throws JMSException {
        consumer.setMessageListener(listener);
    }
}
