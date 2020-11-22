package br.com.fcoromoto.estudo.alura.mensageria.jms.teste.topico;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.topico.ProdutorTopico;
import br.com.fcoromoto.estudo.alura.mensageria.jms.modelo.Pedido;
import br.com.fcoromoto.estudo.alura.mensageria.jms.modelo.PedidoFactory;
import lombok.extern.slf4j.Slf4j;

import javax.jms.ObjectMessage;

@Slf4j
public class TesteProdutorPedidoTopico {

    public static void main(String[] args) throws Exception {
        boolean enviarMensagemFiltrada = isMensagemFiltrada(args);

        if (enviarMensagemFiltrada) {
            enviarMensagemFiltrada();
        } else {
            enviarMensagem();
        }
    }

    private static boolean isMensagemFiltrada(String[] args) {
        try {
            return Boolean.parseBoolean(args[0]);
        } catch (Exception e) {
            return false;
        }
    }

    private static void enviarMensagem() throws Exception {
        try (ProdutorTopico produtor = ProdutorTopico.init()) {
            Pedido pedido = new PedidoFactory().geraPedidoComValores();

            log.info(pedido.toString());
            produtor.enviarMensagem(pedido);
        }
    }

    private static void enviarMensagemFiltrada() throws Exception {
        try (ProdutorTopico produtor = ProdutorTopico.init()) {

            Pedido pedido = new PedidoFactory().geraPedidoComValores();
            log.info(pedido.toString());

            ObjectMessage objectMessage = produtor.criarMensagem(pedido);
            objectMessage.setBooleanProperty("filtro", true);

            produtor.enviarMensagem(objectMessage);
        }
    }
}
