package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBDAO {
	private Connection connection;
	
	public DBDAO() throws ClassNotFoundException {
		this.connection = new Conexao().getConnection();
	}
	
	public void criarTabelaReu() {
		String CREATE_TABLE_REU_QUERY = "CREATE TABLE IF NOT EXISTS reu(\n" +
										"id integer primary key, \n" +
										"nome String, \n" +
										"cpf String, \n" + 
										"nascimento String, \n" +
										");";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(CREATE_TABLE_REU_QUERY);
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
