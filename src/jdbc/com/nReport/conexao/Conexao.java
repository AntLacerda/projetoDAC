package jdbc.com.nReport.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private String url = "jdbc:postgresql://localhost:5432/dac";
	private String usuario = "postgres";
	private String senha = "admin";
	
	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("org.postgresql.Driver");
			//System.out.println("Conexao realizada com sucesso!");
			return DriverManager.getConnection(url, usuario, senha);
		} catch(SQLException e) {
			System.out.println("Erro ao conectar ao banco: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
