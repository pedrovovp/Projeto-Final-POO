package operacoes;

import database.OperationException;
import database.dao.*;

import javax.swing.*;

/** Classe que agrupa os métodos de exclusão para cada operação */

public class Exclusao {
    public FornecedorDAOImpl fornecedorDAO;
    public LocalizacaoDAOImpl localizacaoDAO;
    public LojaDAOImpl lojaDAO;
    public ProdutoDAOImpl produtoDAO;
    public Utils utils;
    private final Listagem listagem;

    public Exclusao() {
        fornecedorDAO = new FornecedorDAOImpl();
        localizacaoDAO = new LocalizacaoDAOImpl();
        lojaDAO = new LojaDAOImpl();
        produtoDAO = new ProdutoDAOImpl();
        utils = new Utils();
        listagem = new Listagem();
    }

    public void opcoes(String inputObj) throws OperationException {
        switch (inputObj) {
            case "1" -> excluirFornecedor();
            case "2" -> excluirLocalizacao();
            case "3" -> excluirLoja();
            case "4" -> excluirProduto();
        }
    }

    private void excluirFornecedor() throws OperationException {
        listagem.opcoes("1");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do fornecedor que deseja excluir"));

        fornecedorDAO.excluir(id);
    }

    private void excluirLocalizacao() throws OperationException {
        listagem.opcoes("2");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da localização que deseja excluir"));

        localizacaoDAO.excluir(id);
    }

    private void excluirLoja() throws OperationException {
        listagem.opcoes("3");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da loja que deseja excluir"));

        lojaDAO.excluir(id);
    }

    private void excluirProduto() throws OperationException {
        listagem.opcoes("4");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do produto que deseja excluir"));

        produtoDAO.excluir(id);
    }

}
