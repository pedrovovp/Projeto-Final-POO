package database.dao;

import database.OperationException;

import java.util.ArrayList;

public interface InterfaceDAO<T> {

    void incluir(T obj);

    void alterar(T obj);

    T consultar(int id) throws OperationException;

    ArrayList<T> listar() throws OperationException;

    void excluir(int id);
}
