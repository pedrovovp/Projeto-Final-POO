package database.dao;

import database.OperationException;
import domain.Produto;

import java.util.ArrayList;

public interface ProdutoDAO {
    void incluir(Produto produto, int id_loja);

    Produto consultar(int id) throws OperationException;

    ArrayList<Produto> listar() throws OperationException;

    ArrayList<Produto> listarProdutosPorLoja(int id_loja) throws OperationException;

    void alterar(Produto produto);

    void excluir(int id);
}
