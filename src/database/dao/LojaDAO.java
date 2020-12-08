package database.dao;

import domain.Loja;

import java.util.ArrayList;

public interface LojaDAO {
    public void incluir(Loja loja);

    Loja consultar(int id);

    ArrayList<Loja> listar();

    void alterar(Loja loja);

    void excluir(int id);
}
