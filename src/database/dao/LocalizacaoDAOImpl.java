package database.dao;

import database.Conexao;
import database.OperationException;
import domain.Localizacao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe utilizada para executar as operações no banco de dados,
 * que envolvem a Localizacao.
 */
public class LocalizacaoDAOImpl implements InterfaceDAO<Localizacao> {
    public void incluir(Localizacao localizacao) {
        String incluir = "INSERT INTO LOCALIZACAO VALUES (" + localizacao.getId() + ", '"
                + localizacao.getEstado() + "', '"
                + localizacao.getEndereco() + "', '"
                + localizacao.getBairro() + "', '"
                + localizacao.getCidade() + "');";

        Conexao conexao = new Conexao();
        conexao.executarDML(incluir);
    }

    public Localizacao consultar(int id) throws OperationException {
        Conexao conexao = new Conexao();
        Localizacao localizacao = null;
        try {
            String consulta = "SELECT * FROM LOCALIZACAO WHERE id_loc = '" + id + "'";
            ResultSet rs = conexao.executarConsulta(consulta);

            if(rs.next()) {
                String estado = rs.getString("estado");
                String endereco = rs.getString("endereco");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                localizacao = new Localizacao(endereco, bairro, cidade, estado);
                localizacao.setId(id);
            }
        } catch (SQLException ex) {
            throw new OperationException("Localizacao", "Consultar", ex);
        } finally {
            conexao.desconectar();
        }

        return localizacao;
    }

    public ArrayList<Localizacao> listar() throws OperationException {
        Conexao conexao = new Conexao();
        ArrayList<Localizacao> localizacoes = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM LOCALIZACAO";
            ResultSet rs = conexao.executarConsulta(consulta);

            while(rs.next()) {
                int id = rs.getInt("id_loc");
                String estado = rs.getString("estado");
                String endereco = rs.getString("endereco");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                Localizacao localizacao = new Localizacao(endereco, bairro, cidade, estado);
                localizacao.setId(id);
                localizacoes.add(localizacao);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new OperationException("Localizacao", "Listar", ex);
        } finally {
            conexao.desconectar();
        }

        return localizacoes;
    }

    public void alterar(Localizacao localizacao) {
        String update = "UPDATE LOCALIZACAO SET estado = '" + localizacao.getEstado() +
                "', endereco = '" + localizacao.getEndereco() +
                "', bairro = '" + localizacao.getBairro() +
                "', cidade = '" + localizacao.getCidade() +
                "' WHERE id_loc = '" +
                localizacao.getId() + "'";
        Conexao conexao = new Conexao();
        conexao.executarDML(update);
    }

    public void excluir(int id) {
        String delete = "DELETE FROM LOCALIZACAO WHERE id_loc='" + id + "'";

        Conexao conexao = new Conexao();
        conexao.executarDML(delete);
    }
}
