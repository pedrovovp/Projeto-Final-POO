package operacoes;

import database.OperationException;
import database.dao.*;
import domain.Fornecedor;
import domain.Localizacao;
import domain.Loja;
import domain.Produto;

import javax.swing.*;
import java.util.ArrayList;

/** Classe que agrupa os métodos de listagem para cada operação */
public class Listagem {
    public FornecedorDAOImpl fornecedorDAO;
    public LocalizacaoDAOImpl localizacaoDAO;
    public LojaDAOImpl lojaDAO;
    public ProdutoDAOImpl produtoDAO;
    public Utils utils;

    public Listagem() {
        fornecedorDAO = new FornecedorDAOImpl();
        localizacaoDAO = new LocalizacaoDAOImpl();
        lojaDAO = new LojaDAOImpl();
        produtoDAO = new ProdutoDAOImpl();
        utils = new Utils();
    }

    public void opcoes(String inputObj) throws OperationException {
        switch (inputObj) {
            case "1" -> listarForn();
            case "2" -> listarLoc();
            case "3" -> listarLojas();
            case "4" -> listarProdutos();
        }
    }

    private void listarForn() throws OperationException {
        ArrayList<Fornecedor> fornecedores = fornecedorDAO.listar();

        StringBuilder s = new StringBuilder();
        for (Fornecedor fornecedor : fornecedores) {
            s.append(fornecedor.toString()).append('\n');
        }

        JOptionPane.showMessageDialog(null, s);
    }

    private void listarLoc() throws OperationException {
        ArrayList<Localizacao> localizacoes = localizacaoDAO.listar();

        StringBuilder s = new StringBuilder();
        for (Localizacao localizacao : localizacoes) {
            s.append(localizacao.toString()).append('\n');
        }

        JOptionPane.showMessageDialog(null, s);
    }

    private void listarLojas() throws OperationException {
        ArrayList<Loja> lojas = lojaDAO.listar();

        StringBuilder s = new StringBuilder();
        for (Loja loja : lojas) {
            s.append(loja.toString()).append('\n');
        }

        JOptionPane.showMessageDialog(null, s);
    }

    private void listarProdutos() throws OperationException {
        ArrayList<Produto> produtos = produtoDAO.listar();

        StringBuilder s = new StringBuilder();
        for (Produto produto : produtos) {
            s.append(produto.toString()).append('\n');
        }

        JOptionPane.showMessageDialog(null, s);
    }
}
