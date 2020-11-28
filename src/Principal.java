import java.sql.Connection;

public class Principal {
    public static void main(String[] args) {
        JdbcConnection db = new JdbcConnection();
        db.getConnection();
        db.createTable("CREATE TABLE Fornecedores(" +
                "id int," +
                "nome varchar(80) NOT NULL," +
                "id_localizacao int," +
                "FOREIGN KEY (id_localizacao) REFERENCES Persons(id_localizacao)" +
                "PRIMARY KEY (id)" +
                ");");
        db.closeConnection();
    }
}
