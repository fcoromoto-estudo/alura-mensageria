package br.com.fcoromoto.estudo.alura.mensageria.jms.teste.topico;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.topico.ProdutorTopico;

public class TesteProdutorTopico {

    public static void main(String[] args) throws Exception {
        ProdutorTopico produtor = ProdutorTopico.init();

        for (int i = 1; i <= 100; i++) {
            produtor.enviarMensagem("Teste enviando msg no topico " + i);
        }

        produtor.close();
    }
}
