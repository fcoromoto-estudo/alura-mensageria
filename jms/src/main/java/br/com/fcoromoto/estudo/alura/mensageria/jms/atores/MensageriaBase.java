package br.com.fcoromoto.estudo.alura.mensageria.jms.atores;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Closeable;
import java.util.Objects;

@Slf4j
public abstract class MensageriaBase implements Closeable {

    private ConnectionFactory factory;

    @Getter(AccessLevel.PROTECTED)
    private Connection connection;

    @Getter(AccessLevel.PROTECTED)
    private InitialContext context;

    @Getter(AccessLevel.PROTECTED)
    private Destination destination;

    @Getter
    private Session session;

    public void startConnection() throws Exception {
        context = new InitialContext();
        factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        connection = createConnection();
        destination = createDestination();
        connection.start();
        session = createSession();
    }

    public Connection createConnection() throws JMSException {
        return factory.createConnection();
    }

    protected Session createSession() throws JMSException {
        return getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public abstract Destination createDestination() throws NamingException;

    public void close() {
        log.info("Close");

        if (Objects.nonNull(connection)) {
            try {
                connection.close();
                log.info("Fechando connection");
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }

        if (Objects.nonNull(context)) {
            try {
                context.close();
                log.info("Fechando context");
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void commit() {
        try {
            session.commit();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollback() {
        try {
            session.rollback();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
