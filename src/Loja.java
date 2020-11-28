import java.util.ArrayList;

public class Loja {
    private int id;
    private String nome;
    private Localizacao localizacao;
    private ArrayList<Produto> produtos;
    static int contadorLoja = 0;

    public Loja(String nome, Localizacao localizacao, ArrayList<Produto> produtos) {
        this.localizacao = localizacao;
        this.produtos = produtos;
        this.id = contadorLoja;
        this.nome = nome;
        this.contadorLoja++;
    }
}
