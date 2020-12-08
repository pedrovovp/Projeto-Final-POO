package database.dao;

import domain.Fornecedor;

import java.util.ArrayList;

public interface FornecedorDAO {
    public void incluir(Fornecedor fornecedor);

    Fornecedor consultar(int it);

    ArrayList<Fornecedor> listar();

    void alterar(Fornecedor fornecedor);

    void excluir(int id);
}
