package br.com.fcoromoto.estudo.alura.mensageria.jms.teste.topico;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.topico.ProdutorTopico;
import lombok.extern.slf4j.Slf4j;

import javax.jms.TextMessage;

@Slf4j
public class TesteProdutorTopico {

    public static void main(String[] args) throws Exception {
        boolean enviarMensagemFiltrada = Boolean.parseBoolean(args[0]);

        if (enviarMensagemFiltrada) {
            enviarMensagemFiltrada();
        } else {
            enviarMensagem();
        }
    }

    private static void enviarMensagem() throws Exception {
        ProdutorTopico produtor = ProdutorTopico.init();

        for (int i = 1; i <= 100; i++) {
            String texto = "Teste " + i;
            log.info("enviando mensagem: " + texto);
            produtor.enviarMensagem(texto);
        }

        produtor.close();
    }

    private static void enviarMensagemFiltrada() throws Exception {
        ProdutorTopico produtor = ProdutorTopico.init();

        for (int i = 1; i <= 100; i++) {
            boolean isMensagemFiltrada = (i % 2) == 0;

            String texto = "Teste " + i + (isMensagemFiltrada ? " mensagem filtrada" : "");
            log.info("enviando mensagem: " + texto);

            TextMessage textMessage = produtor.criarMensagem(texto);
            textMessage.setBooleanProperty("filtro", isMensagemFiltrada);
            produtor.enviarMensagem(textMessage);
        }

        produtor.close();
    }
}
