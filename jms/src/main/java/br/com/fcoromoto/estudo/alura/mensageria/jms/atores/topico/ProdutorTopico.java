package br.com.fcoromoto.estudo.alura.mensageria.jms.atores.topico;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.ProdutorBase;

import javax.jms.Topic;
import javax.naming.NamingException;

public class ProdutorTopico extends ProdutorBase {


    public static ProdutorTopico init() throws Exception {
        ProdutorTopico consumidor = new ProdutorTopico();
        consumidor.start();
        return consumidor;
    }

    public Topic createDestination() throws NamingException {
        return (Topic) getContext().lookup("loja");
    }
}
