package database.dao;

import domain.Produto;

import java.util.ArrayList;

public interface ProdutoDAO {
    void incluir(Produto produto, int id_loja);

    Produto consultar(int id);

    ArrayList<Produto> listar();

    ArrayList<Produto> listarProdutosPorLoja(int id_loja);

    void alterar(Produto produto);

    void excluir(int id);
}
