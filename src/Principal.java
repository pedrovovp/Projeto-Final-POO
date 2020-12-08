import database.Tabelas;
import database.dao.*;
import domain.Fornecedor;
import domain.Localizacao;
import domain.Loja;
import domain.Produto;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Principal {
    public static FornecedorDAOImpl fornecedorDAO = new FornecedorDAOImpl();
    public static LocalizacaoDAOImpl localizacaoDAO = new LocalizacaoDAOImpl();
    public static LojaDAOImpl lojaDAO = new LojaDAOImpl();
    public static ProdutoDAOImpl produtoDAO = new ProdutoDAOImpl();
    public static Utils utils = new Utils();

    public static void main(String[] args) {
        Tabelas tabelas = new Tabelas();
        tabelas.criarTabelas();

        int confirm = 0;
        while(confirm == JOptionPane.YES_OPTION) {
            String[] escolhas = {"Selecione...", "1", "2", "3", "4", "5"};
            String inputOperacoes = (String) JOptionPane.showInputDialog(null, "Escolha a operação\n" +
                            "1: Inclusão\n" +
                            "2: Exclusão\n" +
                            "3: Alteração\n" +
                            "4: Consulta\n" +
                            "5: Lista",
                    "Sistema de Gerenciamento de Dados de Loja", JOptionPane.INFORMATION_MESSAGE, null, escolhas, escolhas[0]);

            String[] escolhas2 = {"Selecione...", "1", "2", "3", "4"};
            String inputObj = (String) JOptionPane.showInputDialog(null, "Escolha o objeto\n" +
                            "1: Fornecedor\n" +
                            "2: Localizacao\n" +
                            "3: Loja\n" +
                            "4: Produto\n",
                    "Sistema de Gerenciamento de Dados de Loja", JOptionPane.INFORMATION_MESSAGE, null, escolhas2, escolhas2[0]);

            switch (inputOperacoes) {
                case "1" -> inclusao(inputObj);
                case "2" -> exclusao(inputObj);
                case "3" -> alteracao(inputObj);
                case "4" -> consulta(inputObj);
                case "5" -> lista(inputObj);
            }
            confirm = JOptionPane.showConfirmDialog(null, "Deseja continuar?");
        }
    }

    private static void consulta(String inputObj) {
        switch (inputObj) {
            case "1" -> consultarForn();
            case "2" -> consultarLoc();
            case "3" -> consultarLoja();
            case "4" -> consultarProd();
        }
    }

    static void incluirFornecedor() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do fornecedor");
        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone do fornecedor");
        String cnpj = JOptionPane.showInputDialog(null, "Digite o cnpj do fornecedor");

        Fornecedor fornecedor = new Fornecedor(nome, telefone, cnpj);
        int qntd = utils.contarTuplas("FORNECEDOR");
        fornecedor.setId(qntd);
        fornecedorDAO.incluir(fornecedor);
    }

    static void incluirLocalizacao() {
        String endereco = JOptionPane.showInputDialog(null, "Digite o endereco da localizacao");
        String bairro = JOptionPane.showInputDialog(null, "Digite o bairro da localizacao");
        String cidade = JOptionPane.showInputDialog(null, "Digite o cidade da localizacao");
        String estado = JOptionPane.showInputDialog(null, "Digite o estado da localizacao");

        Localizacao localizacao = new Localizacao(endereco, bairro, cidade, estado);
        int qntd = utils.contarTuplas("LOCALIZACAO");
        localizacao.setId(qntd);
        localizacaoDAO.incluir(localizacao);
    }

    static void incluirLoja() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da loja");
        listarLoc();
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

    static void incluirProduto(int id_loja) {
        int estoque = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o estoque do produto"));
        double precoDeCusto = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preço de custo do produto"));
        double precoDeVenda = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o preço de venda do produto"));
        String dataUltimaCompra = JOptionPane.showInputDialog(null, "Digite a data de última compra do produto");
        lista("1");
        int id_loc = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do fornecedor do produto"));
        Fornecedor fornecedor = fornecedorDAO.consultar(id_loc);

        Produto produto = new Produto(estoque, precoDeCusto, precoDeVenda, dataUltimaCompra, fornecedor);
        int qntd = utils.contarTuplas("PRODUTO");
        produto.setId(qntd);
        produtoDAO.incluir(produto, id_loja);
    }

    static void inclusao(String inputObj) {
        switch (inputObj) {
            case "1" -> incluirFornecedor();
            case "2" -> incluirLocalizacao();
            case "3" -> incluirLoja();
            case "4" -> {
                lista("3");
                int id_loja = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da loja à qual deseja adicionar o produto"));
                incluirProduto(id_loja);
            }
        }
    }

    static void excluirFornecedor() {
        lista("1");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do fornecedor que deseja excluir"));

        fornecedorDAO.excluir(id);
    }

    static void excluirLocalizacao() {
        lista("2");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da localização que deseja excluir"));

        localizacaoDAO.excluir(id);
    }

    static void excluirLoja() {
        lista("3");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da loja que deseja excluir"));

        lojaDAO.excluir(id);
    }

    static void excluirProduto() {
        lista("4");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do produto que deseja excluir"));

        produtoDAO.excluir(id);
    }

    static void exclusao(String inputObj) {
        switch (inputObj) {
            case "1" -> excluirFornecedor();
            case "2" -> excluirLocalizacao();
            case "3" -> excluirLoja();
            case "4" -> excluirProduto();
        }
    }

    private static void alteracao(String inputObj) {
        switch (inputObj) {
            case "1" -> alterarFornecedor();
            case "2" -> alterarLocalizacao();
            case "3" -> alterarLoja();
            case "4" -> alterarProduto();
        }
    }

    private static void alterarFornecedor() {
        lista("1");
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

    private static void alterarLocalizacao() {
        lista("2");
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

    private static void alterarLoja() {
        lista("3");
        int id_loja = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da loja que deseja alterar "));

        Loja lojaOld = lojaDAO.consultar(id_loja);

        JOptionPane.showMessageDialog(null, "Digite os dados que deseja alterar, ou deixe em branco os que não deseja alterar");
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da loja");
        lista("2");
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
                    incluirProduto(id_loja);
                    break;
                case "2":
                    excluirProduto();
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

    private static void alterarProduto() {
        lista("4");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do produto que deseja alterar"));

        Produto produtoOld = produtoDAO.consultar(id);

        JOptionPane.showMessageDialog(null, "Digite os dados que deseja alterar, ou deixe em branco os que não deseja alterar");

        String estoque = JOptionPane.showInputDialog(null, "Digite o estoque do produto");
        String precoDeCusto = JOptionPane.showInputDialog(null, "Digite o preço de custo do produto");
        String precoDeVenda = JOptionPane.showInputDialog(null, "Digite o preço de venda do produto");
        String dataUltimaCompra = JOptionPane.showInputDialog(null, "Digite a data de última compra do produto");
        lista("1");
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

    static Localizacao consultarLoc() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da localização que deseja consultar"));
        Localizacao localizacao = localizacaoDAO.consultar(id);
        JOptionPane.showMessageDialog(null, localizacao.toString());
        return localizacao;
    }

    static Fornecedor consultarForn() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do fornecedor que deseja consultar"));
        Fornecedor fornecedor = fornecedorDAO.consultar(id);
        JOptionPane.showMessageDialog(null, fornecedor.toString());
        return fornecedor;
    }

    private static Loja consultarLoja() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da loja que deseja consultar"));
        Loja loja = lojaDAO.consultar(id);
        JOptionPane.showMessageDialog(null, loja.toString());
        return loja;
    }

    private static Produto consultarProd() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id da produto que deseja consultar"));
        Produto produto = produtoDAO.consultar(id);
        JOptionPane.showMessageDialog(null, produto.toString());
        return produto;
    }

    static void lista(String inputObj) {
        switch (inputObj) {
            case "1" -> listarForn();
            case "2" -> listarLoc();
            case "3" -> listarLojas();
            case "4" -> listarProdutos();
        }
    }

    private static void listarForn() {
        ArrayList<Fornecedor> fornecedores = fornecedorDAO.listar();

        StringBuilder s = new StringBuilder();
        for(Fornecedor fornecedor: fornecedores) {
            s.append(fornecedor.toString()).append('\n');
        }

        JOptionPane.showMessageDialog(null, s);
    }

    private static void listarLoc() {
        ArrayList<Localizacao> localizacoes = localizacaoDAO.listar();

        StringBuilder s = new StringBuilder();
        for(Localizacao localizacao: localizacoes) {
            s.append(localizacao.toString()).append('\n');
        }

        JOptionPane.showMessageDialog(null, s);
    }

    private static void listarLojas() {
        ArrayList<Loja> lojas = lojaDAO.listar();

        StringBuilder s = new StringBuilder();
        for(Loja loja: lojas) {
            s.append(loja.toString()).append('\n');
        }

        JOptionPane.showMessageDialog(null, s);
    }

    private static void listarProdutos() {
        ArrayList<Produto> produtos = produtoDAO.listar();

        StringBuilder s = new StringBuilder();
        for(Produto produto: produtos) {
            s.append(produto.toString()).append('\n');
        }

        JOptionPane.showMessageDialog(null, s);
    }
}
