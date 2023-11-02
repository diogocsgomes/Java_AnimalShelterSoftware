import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    //private static final String URL = "jdbc:sqlite:C:/Users/diogo/DataGripProjects/projetoDeTeste/identifier.sqlite";
    private static final String URL = "jdbc:sqlite:C:\\Users\\diogo\\OneDrive\\Ambiente de Trabalho\\ISEC\\3ºano\\Gestão de Projeto de Software\\identifier2.sqlite";

    Connection conn = null;
    public DatabaseManager() throws SQLException, ClassNotFoundException {
        // Carregar o driver SQLite (não é necessário para versões recentes do JDBC)
        //Class.forName("org.sqlite.JDBC");

        // Estabelecer a conexão
        //String url = "jdbc:sqlite:C:/Users/diogo/DataGripProjects/projetoDeTeste/identifier.sqlite";
        conn = DriverManager.getConnection(URL);

        System.out.println("Conexão estabelecida com sucesso!");
    }

    public Connection getConn(){
        return conn;
    }


}
