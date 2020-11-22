package br.com.fcoromoto.estudo.alura.mensageria.jms.atores;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Closeable;
import java.util.Objects;
import java.util.Properties;

import static br.com.fcoromoto.estudo.alura.mensageria.jms.util.ThrowingConsumer.wrapper;

public abstract class MensageriaBase implements Closeable {

    private InitialContext context;
    private ConnectionFactory factory;
    private Connection connection;
    private Destination queue;
    private Session session;



    private static InitialContext createInitialContext() throws NamingException {
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty("java.naming.provider.url", "tcp://localhost:61616");
        props.setProperty("queue.financeiro", "fila.financeiro");
        return new InitialContext(props);
    }

    public void startConnection() throws Exception {
        context = createInitialContext();
        factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        connection = factory.createConnection();
        queue = (Destination) context.lookup("financeiro");
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public void close(){
        wrapper(o -> {
            if(Objects.nonNull(connection)){
                connection.close();
            }

            if(Objects.nonNull(context)){
                context.close();
            }
        });
    }

    public Destination getQueue() {
        return queue;
    }

    public Session getSession() {
        return session;
    }
}
