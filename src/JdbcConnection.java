import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {
    private String jdbcDriver = "org.postgresql.Driver";
    private Connection con = null;

    public Connection getConnection() {
        try {
            if (con == null) {
                Class.forName(jdbcDriver);
                con = DriverManager.getConnection("jdbc:postgresql://localhost/loja?user=postgres&password=admin&ssl=false");
                System.out.println("Conectou no banco de dados.");
            } else if (con.isClosed()) {
                con = null;
                return getConnection();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Não conseguiu conectar no BD.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro: Não encontrou o driver do BD.");
            e.printStackTrace();
        }
        return con;
    }

    public void createTable(String instrucaoSQL) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(instrucaoSQL);
            st.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                //TODO: use um sistema de log apropriado.
                e.printStackTrace();
            }
        }
    }
}
