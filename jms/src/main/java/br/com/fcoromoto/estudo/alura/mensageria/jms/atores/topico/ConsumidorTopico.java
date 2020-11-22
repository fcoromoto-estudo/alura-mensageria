package br.com.fcoromoto.estudo.alura.mensageria.jms.atores.topico;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.ConsumidorBase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Topic;
import javax.naming.NamingException;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumidorTopico extends ConsumidorBase {

    private String sessionID;
    private String selector;

    public static ConsumidorTopico init(String sessionID) throws Exception {
        return init(sessionID, null);
    }

    public static ConsumidorTopico init(String sessionID, String selector) throws Exception {
        ConsumidorTopico consumidor = new ConsumidorTopico(sessionID, selector);
        consumidor.startConsumidor();
        return consumidor;
    }

    public Topic createDestination() throws NamingException {
        return (Topic) getContext().lookup("loja");
    }

    @Override
    protected MessageConsumer createConsumer() throws Exception {
        Topic destination = (Topic) getDestination();
        if (Objects.isNull(selector)) {
            return getSession().createDurableSubscriber(destination, "assinatura");
        }
        return getSession().createDurableSubscriber(destination, "assinatura", selector, false);
    }

    @Override
    public Connection createConnection() throws JMSException {
        Connection connection = super.createConnection();
        connection.setClientID(sessionID);
        return connection;
    }
}
