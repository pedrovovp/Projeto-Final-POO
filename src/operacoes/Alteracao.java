package operacoes;

import database.OperationException;
import database.dao.*;
import domain.Fornecedor;
import domain.Localizacao;
import domain.Loja;
import domain.Produto;

import javax.swing.*;
import java.util.ArrayList;


/** Classe que agrupa os métodos de alteração para cada operação */

public class Alteracao {
    public FornecedorDAOImpl fornecedorDAO;
    public LocalizacaoDAOImpl localizacaoDAO;
    public LojaDAOImpl lojaDAO;
    public ProdutoDAOImpl produtoDAO;
    public Utils utils;
    private final Listagem listagem;
    private final Inclusao inclusao;
    private final Exclusao exclusao;

    public Alteracao() {
        fornecedorDAO = new FornecedorDAOImpl();
        localizacaoDAO = new LocalizacaoDAOImpl();
        lojaDAO = new LojaDAOImpl();
        produtoDAO = new ProdutoDAOImpl();
        utils = new Utils();
        listagem = new Listagem();
        inclusao = new Inclusao();
        exclusao = new Exclusao();
    }

    public void opcoes(String inputObj) throws OperationException {
        switch (inputObj) {
            case "1" -> alterarFornecedor();
            case "2" -> alterarLocalizacao();
            case "3" -> alterarLoja();
            case "4" -> alterarProduto();
        }
    }

    public void alterarFornecedor() throws OperationException {
        listagem.opcoes("1");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do fornecedor que deseja alterar"));

        Fornecedor fornecedorOld = fornecedorDAO.consultar(id);

        JOptionPane.showMessageDialog(null, "Digite os dados que deseja alterar, ou deixe em branco os que não deseja alterar");
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do fornecedor");
        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone do fornecedor");
        String cnpj = JOptionPane.showInputDialog(null, "Digite o cnpj do fornecedor");

        Fornecedor fornecedorNew = new Fornecedor(nome.isEmpty() ? fornecedorOld.getNome() : nome,
                telefone.isEmpty() ? fornecedorOld.getTelefone() : telefone,
                cnpj.isEmpty() ? fornecedorOld.getCnpj() : cnpj);
        fornecedorNew.setId(id);

        fornecedorDAO.alterar(fornecedorNew);
    }

    public void alterarLocalizacao() throws OperationException {
        listagem.opcoes("2");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da localização que deseja alterar"));

        Localizacao localizacaoOld = localizacaoDAO.consultar(id);

        JOptionPane.showMessageDialog(null, "Digite os dados que deseja alterar, ou deixe em branco os que não deseja alterar");
        String endereco = JOptionPane.showInputDialog(null, "Digite o endereco da localizacao");
        String bairro = JOptionPane.showInputDialog(null, "Digite o bairro da localizacao");
        String cidade = JOptionPane.showInputDialog(null, "Digite o cidade da localizacao");
        String estado = JOptionPane.showInputDialog(null, "Digite o estado da localizacao");

        Localizacao localizacaoNew = new Localizacao(endereco.isEmpty() ? localizacaoOld.getEndereco() : endereco,
                bairro.isEmpty() ? localizacaoOld.getBairro() : bairro,
                cidade.isEmpty() ? localizacaoOld.getCidade() : cidade,
                estado.isEmpty() ? localizacaoOld.getEstado() : estado);
        localizacaoNew.setId(id);

        localizacaoDAO.alterar(localizacaoNew);
    }

    public void alterarLoja() throws OperationException {
        listagem.opcoes("3");
        int id_loja = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da loja que deseja alterar "));

        Loja lojaOld = lojaDAO.consultar(id_loja);

        JOptionPane.showMessageDialog(null, "Digite os dados que deseja alterar, ou deixe em branco os que não deseja alterar");
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da loja");
        listagem.opcoes("2");
        String id_loc = JOptionPane.showInputDialog(null, "Digite o id da localização da loja");
        Localizacao localizacao = null;
        if(!id_loc.isEmpty()) localizacao = localizacaoDAO.consultar(Integer.parseInt(id_loc));

        int confirm = JOptionPane.showConfirmDialog(null, "Deseja excluir ou incluir um produto?");
        while (confirm == JOptionPane.YES_OPTION) {
            String[] escolhas = {"Selecione...", "1", "2", "3", "4", "5"};
            String input = (String) JOptionPane.showInputDialog(null, "Escolha a operação\n" +
                            "1: Incluir Produto\n" +
                            "2: Excluir Produto\n" +
                            "3: Continuar",
                    "Produto", JOptionPane.INFORMATION_MESSAGE, null, escolhas, escolhas[0]);

            switch (input) {
                case "1":
                    inclusao.incluirProduto(id_loja);
                    break;
                case "2":
                    exclusao.opcoes("4");
                    break;
            }
            confirm = JOptionPane.showConfirmDialog(null, "Deseja excluir ou incluir um produto?");
        }
        ArrayList<Produto> produtos = produtoDAO.listarProdutosPorLoja(Loja.contadorLoja);

        Loja loja = new Loja(nome.isEmpty() ? lojaOld.getNome() : nome,
                localizacao == null ? lojaOld.getLocalizacao() : localizacao,
                produtos);
        loja.setId(id_loja);
        lojaDAO.alterar(loja);
    }

    public void alterarProduto() throws OperationException {
        listagem.opcoes("4");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do produto que deseja alterar"));

        Produto produtoOld = produtoDAO.consultar(id);

        JOptionPane.showMessageDialog(null, "Digite os dados que deseja alterar, ou deixe em branco os que não deseja alterar");

        String estoque = JOptionPane.showInputDialog(null, "Digite o estoque do produto");
        String precoDeCusto = JOptionPane.showInputDialog(null, "Digite o preço de custo do produto");
        String precoDeVenda = JOptionPane.showInputDialog(null, "Digite o preço de venda do produto");
        String dataUltimaCompra = JOptionPane.showInputDialog(null, "Digite a data de última compra do produto");
        listagem.opcoes("1");
        String id_loc = JOptionPane.showInputDialog(null, "Digite o id do fornecedor do produto");
        Fornecedor fornecedor = null;
        if(!id_loc.isEmpty())
            fornecedor = fornecedorDAO.consultar(Integer.parseInt(id_loc));

        Produto produto = new Produto(estoque.isEmpty() ? produtoOld.getEstoque() : Integer.parseInt(estoque),
                precoDeCusto.isEmpty() ? produtoOld.getPrecoDeCusto() : Double.parseDouble(precoDeCusto),
                precoDeVenda.isEmpty() ? produtoOld.getPrecoDeVenda() : Double.parseDouble(precoDeVenda),
                dataUltimaCompra.isEmpty() ? produtoOld.getDataUltimaCompra() : dataUltimaCompra,
                fornecedor == null ? produtoOld.getFornecedor() : fornecedor);


        produtoDAO.alterar(produto);
    }
}
