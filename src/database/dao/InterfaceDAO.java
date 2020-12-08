package database.dao;

import java.util.ArrayList;

public interface InterfaceDAO<T> {

    public void incluir(T obj);

    public void alterar(T obj);

    public T consultar(int id);

    public ArrayList<T> listar();

    public void excluir(int id);
}
