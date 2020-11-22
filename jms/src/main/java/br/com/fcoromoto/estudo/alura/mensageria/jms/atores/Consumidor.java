package br.com.fcoromoto.estudo.alura.mensageria.jms.atores;

import javax.jms.MessageConsumer;

public class Consumidor extends MensageriaBase {

    private  MessageConsumer consumer;

    private Consumidor() {}

    public static Consumidor init() throws Exception {
        Consumidor consumidor = new Consumidor();
        consumidor.createConsumer();
        return consumidor;
    }

    private void createConsumer() throws Exception {
        super.startConnection();
        consumer = getSession().createConsumer(getQueue());
    }

    public MessageConsumer getConsumer() {
        return consumer;
    }
}
