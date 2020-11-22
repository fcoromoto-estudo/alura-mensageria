package br.com.fcoromoto.estudo.alura.mensageria.jms.atores.fila;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.ConsumidorBase;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.naming.NamingException;

public class ConsumidorFila extends ConsumidorBase {

    public static ConsumidorFila init() throws Exception {
        ConsumidorFila consumidor = new ConsumidorFila();
        consumidor.startConsumidor();
        return consumidor;
    }

    public Destination createDestination() throws NamingException {
        return (Destination) getContext().lookup("financeiro");
    }

    @Override
    protected MessageConsumer createConsumer() throws Exception {
        return getSession().createConsumer(getDestination());
    }
}
