package br.com.fcoromoto.estudo.alura.mensageria.jms.atores;

import lombok.extern.slf4j.Slf4j;

import javax.jms.*;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Objects;

@Slf4j
public abstract class ProdutorBase extends MensageriaBase {

    private MessageProducer produtor;

    public void start() throws Exception {
        super.startConnection();
        produtor = createProdutor();
    }

    protected MessageProducer createProdutor() throws Exception {
        return getSession().createProducer(getDestination());
    }

    public void enviarMensagem(String mensagem) {
        TextMessage textMessage = criarMensagem(mensagem);
        enviarMensagem(textMessage);
    }

    public void enviarMensagem(Serializable objeto) {
        ObjectMessage objectMessage = criarMensagem(objeto);
        enviarMensagem(objectMessage);
    }

    public void enviarMensagem(Message mensagem) {
        try {
            produtor.send(mensagem);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TextMessage criarMensagem(String mensagem) {
        try {
            return getSession().createTextMessage(mensagem);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectMessage criarMensagem(Serializable objeto) {
        try {
            return getSession().createObjectMessage(objeto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract Destination createDestination() throws NamingException;

    @Override
    public void close() {
        if (Objects.nonNull(getSession())) {
            try {
                log.info("Fechando sessão");
                getSession().close();
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
        super.close();
    }
}
