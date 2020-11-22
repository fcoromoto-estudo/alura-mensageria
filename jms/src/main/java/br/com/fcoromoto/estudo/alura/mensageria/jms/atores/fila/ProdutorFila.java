package br.com.fcoromoto.estudo.alura.mensageria.jms.atores.fila;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.ProdutorBase;

import javax.jms.Destination;
import javax.naming.NamingException;

public class ProdutorFila extends ProdutorBase {

    public static ProdutorFila init() throws Exception {
        ProdutorFila consumidor = new ProdutorFila();
        consumidor.start();
        return consumidor;
    }

    public Destination createDestination() throws NamingException {
        return (Destination) getContext().lookup("financeiro");
    }
}
