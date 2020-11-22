package br.com.fcoromoto.estudo.alura.mensageria.jms.atores.fila;

import javax.jms.JMSException;
import javax.jms.Session;

public class ConsumidorFilaSession extends ConsumidorFila {

    public static ConsumidorFila initWithSession() throws Exception {
        ConsumidorFilaSession consumidor = new ConsumidorFilaSession();
        consumidor.startConsumidor();
        return consumidor;
    }


    @Override
    protected Session createSession() throws JMSException {
        return getConnection().createSession(true, Session.SESSION_TRANSACTED);
    }
}
