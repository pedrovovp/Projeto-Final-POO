package domain;

import java.util.ArrayList;

/** Classe que agrupa os dados do Objeto Loja */
public class Loja {
    private int id;
    private String nome;
    private Localizacao localizacao;
    private ArrayList<Produto> produtos;
    public static int contadorLoja = 0;

    public Loja(String nome, Localizacao localizacao, ArrayList<Produto> produtos) {
        this.localizacao = localizacao;
        this.produtos = produtos;
        this.id = contadorLoja;
        this.nome = nome;
        this.contadorLoja++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Loja{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localizacao=" + localizacao +
                ", produtos=" + produtos +
                '}';
    }
}
