package br.com.fcoromoto.estudo.alura.mensageria.jms.teste.fila;

import br.com.fcoromoto.estudo.alura.mensageria.jms.atores.fila.ProdutorFila;

public class TesteProdutorFila {

    public static void main(String[] args) throws Exception {
        ProdutorFila produtor = ProdutorFila.init();

        for (int i = 1; i <= 100; i++) {
            produtor.enviarMensagem("Teste " + i);
        }
        produtor.close();
    }
}
