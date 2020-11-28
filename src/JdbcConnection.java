import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {
    private String hostName = "localhost";
    private String userName = "root";
    private String password = "admin123";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String dataBaseName = "loja_db";
    private String dataBasePrefix = "jdbc: mysql:/";
    private String dabaBasePort = "3306";
    private String url = dataBasePrefix + hostName + ":"+dabaBasePort+"/" + dataBaseName + "/";
    private Connection con = null;

    public Connection getConnection() {
        try {
            if (con == null) {
                Class.forName(jdbcDriver);
                con = DriverManager.getConnection(url, userName, password);
            } else if (con.isClosed()) {
                con = null;
                return getConnection();
            }
        } catch (ClassNotFoundException e) {

            //TODO: use um sistema de log apropriado.

            e.printStackTrace();
        } catch (SQLException e) {

            //TODO: use um sistema de log apropriado.

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
