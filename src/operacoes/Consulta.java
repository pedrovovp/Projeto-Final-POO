package operacoes;

import database.OperationException;
import database.dao.FornecedorDAOImpl;
import database.dao.LocalizacaoDAOImpl;
import database.dao.LojaDAOImpl;
import database.dao.ProdutoDAOImpl;
import domain.Fornecedor;
import domain.Localizacao;
import domain.Loja;
import domain.Produto;

import javax.swing.*;

/**
 * Classe que agrupa os métodos de consulta para cada operação
 */

public class Consulta {
    private final FornecedorDAOImpl fornecedorDAO;
    private final LocalizacaoDAOImpl localizacaoDAO;
    private final LojaDAOImpl lojaDAO;
    private final ProdutoDAOImpl produtoDAO;

    public Consulta() {
        fornecedorDAO = new FornecedorDAOImpl();
        localizacaoDAO = new LocalizacaoDAOImpl();
        lojaDAO = new LojaDAOImpl();
        produtoDAO = new ProdutoDAOImpl();
    }

    public void opcoes(String inputObj) throws OperationException {
        switch (inputObj) {
            case "1" -> consultarForn();
            case "2" -> consultarLoc();
            case "3" -> consultarLoja();
            case "4" -> consultarProd();
        }
    }

    public Localizacao consultarLoc() throws OperationException {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da localização que deseja consultar"));
        Localizacao localizacao = localizacaoDAO.consultar(id);
        JOptionPane.showMessageDialog(null, localizacao.toString());
        return localizacao;
    }

    public Fornecedor consultarForn() throws OperationException {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do fornecedor que deseja consultar"));
        Fornecedor fornecedor = fornecedorDAO.consultar(id);
        JOptionPane.showMessageDialog(null, fornecedor.toString());
        return fornecedor;
    }

    public Loja consultarLoja() throws OperationException {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da loja que deseja consultar"));
        Loja loja = lojaDAO.consultar(id);
        JOptionPane.showMessageDialog(null, loja.toString());
        return loja;
    }

    public Produto consultarProd() throws OperationException {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da produto que deseja consultar"));
        Produto produto = produtoDAO.consultar(id);
        JOptionPane.showMessageDialog(null, produto.toString());
        return produto;
    }
}
