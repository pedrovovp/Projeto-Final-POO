package operacoes;

import database.OperationException;
import database.dao.*;
import domain.Fornecedor;
import domain.Localizacao;
import domain.Loja;
import domain.Produto;

import javax.swing.*;
import java.util.ArrayList;

/** Classe que agrupa os métodos de inclusão para cada operação */
public class Inclusao {
    private final FornecedorDAOImpl fornecedorDAO;
    private final LocalizacaoDAOImpl localizacaoDAO;
    private final LojaDAOImpl lojaDAO;
    private final ProdutoDAOImpl produtoDAO;
    private final Utils utils;
    private final Listagem listagem;

    public Inclusao() {
        fornecedorDAO = new FornecedorDAOImpl();
        localizacaoDAO = new LocalizacaoDAOImpl();
        lojaDAO = new LojaDAOImpl();
        produtoDAO = new ProdutoDAOImpl();
        utils = new Utils();
        listagem = new Listagem();
    }

    public void opcoes(String inputObj) throws OperationException {
        switch (inputObj) {
            case "1" -> incluirFornecedor();
            case "2" -> incluirLocalizacao();
            case "3" -> incluirLoja();
            case "4" -> {
                listagem.opcoes("3");
                int id_loja = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da loja à qual deseja adicionar o produto"));
                incluirProduto(id_loja);
            }
        }
    }


    public void incluirFornecedor() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do fornecedor");
        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone do fornecedor");
        String cnpj = JOptionPane.showInputDialog(null, "Digite o cnpj do fornecedor");

        Fornecedor fornecedor = new Fornecedor(nome, telefone, cnpj);
        int qntd = utils.contarTuplas("FORNECEDOR");
        fornecedor.setId(qntd);
        fornecedorDAO.incluir(fornecedor);
    }

    public void incluirLocalizacao() {
        String endereco = JOptionPane.showInputDialog(null, "Digite o endereco da localizacao");
        String bairro = JOptionPane.showInputDialog(null, "Digite o bairro da localizacao");
        String cidade = JOptionPane.showInputDialog(null, "Digite o cidade da localizacao");
        String estado = JOptionPane.showInputDialog(null, "Digite o estado da localizacao");

        Localizacao localizacao = new Localizacao(endereco, bairro, cidade, estado);
        int qntd = utils.contarTuplas("LOCALIZACAO");
        localizacao.setId(qntd);
        localizacaoDAO.incluir(localizacao);
    }

    public void incluirLoja() throws OperationException {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da loja");
        listagem.opcoes("2");
        int id_loc = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da localização da loja"));
        Localizacao localizacao = localizacaoDAO.consultar(id_loc);

        ArrayList<Produto> produtos = produtoDAO.listarProdutosPorLoja(Loja.contadorLoja);

        Loja loja = new Loja(nome, localizacao, null);
        int qntd = utils.contarTuplas("LOJA");
        loja.setId(qntd);
        lojaDAO.incluir(loja);
        int confirm = JOptionPane.showConfirmDialog(null, "Deseja adicionar produto?");
        while (confirm == JOptionPane.YES_OPTION) {
            incluirProduto(loja.getId());
            confirm = JOptionPane.showConfirmDialog(null, "Deseja adicionar produto?");
        }
    }

    public void incluirProduto(int id_loja) throws OperationException {
        int estoque = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o estoque do produto"));
        double precoDeCusto = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preço de custo do produto"));
        double precoDeVenda = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preço de venda do produto"));
        String dataUltimaCompra = JOptionPane.showInputDialog(null, "Digite a data de última compra do produto");
        listagem.opcoes("1");
        int id_loc = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do fornecedor do produto"));
        Fornecedor fornecedor = fornecedorDAO.consultar(id_loc);

        Produto produto = new Produto(estoque, precoDeCusto, precoDeVenda, dataUltimaCompra, fornecedor);
        int qntd = utils.contarTuplas("PRODUTO");
        produto.setId(qntd);
        produtoDAO.incluir(produto, id_loja);
    }
}
