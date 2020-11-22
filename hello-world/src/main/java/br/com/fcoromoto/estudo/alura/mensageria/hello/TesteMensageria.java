package br.com.fcoromoto.estudo.alura.mensageria.hello;

public class TesteMensageria {

    public static void main(String[] args) {
        int max;
        if (args.length == 0) {
            System.out.println("use \"envia\" ou \"consome\" como parametro");
            return;
        }
        ConectorJms conectorJms = new ConectorJms();
        switch (args[0]) {
            case "envia":
                max = parseMax(args);
                conectorJms.enviaMensagens(max);
                break;
            case "consome":
                conectorJms.consomeMensagens();
                break;
        }
        conectorJms.close();
    }

    private static int parseMax(String[] args) {
        if (args.length < 2 || args[1] == null)
            return 1;
        return Integer.parseInt(args[1]);
    }
}
