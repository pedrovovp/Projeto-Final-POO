package database.dao;

import domain.Localizacao;

import java.util.ArrayList;

public interface LocalizacaoDAO {
    public void incluir(Localizacao localizacao);

    Localizacao consultar(int it);

    ArrayList<Localizacao> listar();

    void alterar(Localizacao localizacao);

    void excluir(int id);
}
