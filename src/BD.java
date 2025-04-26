import java.sql.*;

public class BD {
    String jdbc = "jdbc:mysql:";
    String host = "//localhost:3306/"; // inicia com // e finaliza com uma / para separar o database
    String database = "petclub";
    String username = "root";
    String passw = "";
    Connection conexao;

    public void startConnection() throws SQLException {
        this.conexao = DriverManager.getConnection( jdbc+host+database,username,passw);
    }

    public ResultSet runSQLQuery(String sql) throws SQLException {
        return conexao.createStatement().executeQuery(sql);
    }

    public boolean runSQL(String sql) throws SQLException {
        return conexao.createStatement().execute(sql);
    }

    public Connection getConexao() {
        return conexao;
    }

    public void closeConnection() throws SQLException  {
        this.conexao.close();
        System.out.println("Conexão encerrada!");
    }

}
