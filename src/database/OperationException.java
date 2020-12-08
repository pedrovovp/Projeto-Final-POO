package database;

import java.sql.SQLException;

/** Exception que dispara diante de um erro em uma operação */
public class OperationException extends SQLException {
    public OperationException(String tabela, String operacao, SQLException err) {
        super("Erro ao realizar a operação '" + operacao + "' na tabela '" + tabela + "'");
        err.printStackTrace();
    }
}
