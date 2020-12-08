package database.dao;

import database.Conexao;
import domain.Fornecedor;
import domain.Produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAOImpl implements ProdutoDAO{
    @Override
    public void incluir(Produto produto, int id_loja) {
        String incluir = "INSERT INTO PRODUTO VALUES (" + produto.getId() + ", '"
                + produto.getEstoque() + "', '"
                + produto.getPrecoDeCusto() + "', '"
                + produto.getPrecoDeVenda() + "', '"
                + produto.getDataUltimaCompra() + "', '"
                + id_loja + "', '"
                + produto.getFornecedor().getId() + "');";

        Conexao conexao = new Conexao();
        conexao.executarDML(incluir);
    }

    @Override
    public Produto consultar(int id) {
        Conexao conexao = new Conexao();
        Produto produto = null;
        try {
            String consulta = "SELECT * FROM PRODUTO WHERE id_forn = '" + id + "'";
            ResultSet rs = conexao.executarConsulta(consulta);

            if(rs.next())
                produto = construirProduto(rs, id);
        } catch (SQLException ex) {
            System.out.println("Nao conseguiu consultar os dados do Produto.");
        } finally {
            conexao.desconectar();
        }

        return produto;
    }

    @Override
    public ArrayList<Produto> listar() {
        Conexao conexao = new Conexao();
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM PRODUTO";
            ResultSet rs = conexao.executarConsulta(consulta);

            while(rs.next()) {
                int id = rs.getInt("id_forn");
                Produto produto = construirProduto(rs, id);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Nao conseguiu consultar os dados do Produto.");
        } finally {
            conexao.desconectar();
        }

        return produtos;
    }

    @Override
    public ArrayList<Produto> listarProdutosPorLoja(int id_loja) {
        Conexao conexao = new Conexao();
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            String consultaProduto = "SELECT * FROM PRODUTO WHERE id_loja = '" + id_loja + "'";
            ResultSet rsProd = conexao.executarConsulta(consultaProduto);

            while(rsProd.next()) {
                int idProd = rsProd.getInt("id_prod");
                Produto produto = construirProduto(rsProd, idProd);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Nao conseguiu consultar os dados do Produto.");
        } finally {
            conexao.desconectar();
        }

        return produtos;
    }

    @Override
    public void alterar(Produto produto) {
        String update = "UPDATE PRODUTO SET estoque = '" + produto.getEstoque() +
                "', preco_de_custo = '" + produto.getPrecoDeCusto() +
                "', preco_de_venda = '" + produto.getPrecoDeVenda() +
                "', data_ultima_compra = '" + produto.getDataUltimaCompra() +
                "', id_forn = '" + produto.getFornecedor().getId() +
                "' WHERE id_prod = '" +
                produto.getId() + "'";
        Conexao conexao = new Conexao();
        conexao.executarDML(update);
    }

    @Override
    public void excluir(int id) {
        String delete = "DELETE FROM PRODUTO WHERE id_loc='" + id + "'";

        Conexao conexao = new Conexao();
        conexao.executarDML(delete);
    }

    private Produto construirProduto(ResultSet rs, int id) throws SQLException {
        int estoque = rs.getInt("estoque");
        double preco_de_custo = rs.getDouble("preco_de_custo");
        double preco_de_venda = rs.getDouble("preco_de_venda");
        String data_ultima_compra = rs.getString("data_ultima_compra");
        int id_forn = rs.getInt("id_forn");

        FornecedorDAOImpl fornecedorDAO = new FornecedorDAOImpl();
        Fornecedor fornecedor = fornecedorDAO.consultar(id_forn);

        Produto produto = new Produto(estoque, preco_de_custo, preco_de_venda, data_ultima_compra, fornecedor);
        produto.setId(id);
        return produto;
    }
}
