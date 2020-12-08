import database.OperationException;
import database.Tabelas;
import operacoes.*;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) throws OperationException {
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
                case "1" -> new Inclusao().opcoes(inputObj);
                case "2" -> new Exclusao().opcoes(inputObj);
                case "3" -> new Alteracao().opcoes(inputObj);
                case "4" -> new Consulta().opcoes(inputObj);
                case "5" -> new Listagem().opcoes(inputObj);
            }
            confirm = JOptionPane.showConfirmDialog(null, "Deseja continuar?");
        }
    }
}
