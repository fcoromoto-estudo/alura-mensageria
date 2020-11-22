package br.com.fcoromoto.estudo.alura.mensageria.jms.atores;

import lombok.extern.slf4j.Slf4j;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import java.util.Objects;

@Slf4j
public abstract class ProdutorBase extends MensageriaBase {

    private MessageProducer produtor;

    public void start() throws Exception {
        super.startConnection();
        produtor = createProdutor();
    }

    private MessageProducer createProdutor() throws Exception {
        return getSession().createProducer(getDestination());
    }

    public void enviarMensagem(String mensagem) {
        try {
            TextMessage textMessage = getSession().createTextMessage(mensagem);
            produtor.send(textMessage);
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
