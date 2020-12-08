package database.dao;

import database.Conexao;
import database.OperationException;
import domain.Localizacao;
import domain.Loja;
import domain.Produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe utilizada para executar as operações no banco de dados,
 * que envolvem o Loja.
 */
public class LojaDAOImpl implements InterfaceDAO<Loja>{
    @Override
    public void incluir(Loja loja) {
        String incluir = "INSERT INTO LOJA VALUES (" + loja.getId() + ", '"
                + loja.getNome() + "', '"
                + loja.getLocalizacao().getId() + "');";

        Conexao conexao = new Conexao();
        conexao.executarDML(incluir);
    }

    @Override
    public Loja consultar(int id) throws OperationException {
        Conexao conexao = new Conexao();
        Loja loja = null;
        try {

            ProdutoDAOImpl produtoDAO = new ProdutoDAOImpl();
            ArrayList<Produto> produtos = produtoDAO.listarProdutosPorLoja(id);

            String consulta = "SELECT * FROM LOJA WHERE id_loja = '" + id + "'";
            ResultSet rs = conexao.executarConsulta(consulta);

            if(rs.next()) {
                String nome = rs.getString("nome");
                int id_loc = rs.getInt("id_loc");

                LocalizacaoDAOImpl localizacaoDAO = new LocalizacaoDAOImpl();
                Localizacao localizacao = localizacaoDAO.consultar(id_loc);

                loja = new Loja(nome, localizacao, produtos);
                loja.setId(id);
            }
        } catch (SQLException ex) {
            throw new OperationException("Loja", "Consultar", ex);
        } finally {
            conexao.desconectar();
        }

        return loja;
    }

    @Override
    public ArrayList<Loja> listar() throws OperationException {
        Conexao conexao = new Conexao();
        ArrayList<Loja> lojas = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM LOJA";
            ResultSet rs = conexao.executarConsulta(consulta);

            while(rs.next()) {
                int id_loja = rs.getInt("id_loja");
                String nome = rs.getString("nome");
                int id_loc = rs.getInt("id_loc");

                LocalizacaoDAOImpl localizacaoDAO = new LocalizacaoDAOImpl();
                Localizacao localizacao = localizacaoDAO.consultar(id_loc);

                ProdutoDAOImpl produtoDAO = new ProdutoDAOImpl();
                ArrayList<Produto> produtos = produtoDAO.listarProdutosPorLoja(id_loja);

                Loja loja = new Loja(nome, localizacao, produtos);
                loja.setId(id_loja);

                lojas.add(loja);
            }
        } catch (SQLException ex) {
            throw new OperationException("Loja", "Listar", ex);
        } finally {
            conexao.desconectar();
        }

        return lojas;
    }

    @Override
    public void alterar(Loja loja) {
        String update = "UPDATE LOJA SET nome = '" + loja.getNome() +
                "', id_loc = '" + loja.getLocalizacao().getId() +
                "' WHERE id_loja = '" +
                loja.getId() + "'";
        Conexao conexao = new Conexao();
        conexao.executarDML(update);
    }

    @Override
    public void excluir(int id) {
        String delete = "DELETE FROM LOJA WHERE id_loja='" + id + "'";

        Conexao conexao = new Conexao();
        conexao.executarDML(delete);
    }
}
