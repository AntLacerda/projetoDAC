package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBDAO {
	private Connection connection;

	public DBDAO() throws ClassNotFoundException {
		this.connection = new Conexao().getConnection();
	}

	public void criarTabelas() {
		criarTabelaDelegado();
		criarTabelaOcorrencia();
		criarTabelaReu();
		criarTabelaDelegacia();
	}

	public void criarTabelaDelegado() {
		String CREATE_TABLE_DELEGADO_QUERY = "CREATE TABLE IF NOT EXISTS delegado(\n" +
				"id INT PRIMARY KEY,\n" +
				"matricula VARCHAR(50),\n" +
				"nome VARCHAR(100),\n" +
				"contato VARCHAR(100)\n" +
				");";

		try (PreparedStatement stmt = connection.prepareStatement(CREATE_TABLE_DELEGADO_QUERY)) {
			stmt.execute();
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}

	public void criarTabelaOcorrencia() {
		String CREATE_TABLE_OCORRENCIA_QUERY = "CREATE TABLE IF NOT EXISTS ocorrencia(\n" +
				"id INT PRIMARY KEY,\n" +
				"data VARCHAR(100),\n" +
				"hora VARCHAR(100),\n" +
				"local VARCHAR(100),\n" +
				"tipoCrime VARCHAR(100),\n" +
				"idDelegado INT,\n" +
				"FOREIGN KEY (id_delegado) REFERENCES delegado(id_delegado)\n" +
				");";

		try (PreparedStatement stmt = connection.prepareStatement(CREATE_TABLE_OCORRENCIA_QUERY)) {
			stmt.execute();
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}

	public void criarTabelaReu() {
		String CREATE_TABLE_REU_QUERY = "CREATE TABLE IF NOT EXISTS reu(\n" +
				"id INT PRIMARY KEY,\n" +
				"nome VARCHAR(100),\n" +
				"cpf VARCHAR(14)\n" +
				"nascimento VARCHAR(14)\n" +
				");";

		try (PreparedStatement stmt = connection.prepareStatement(CREATE_TABLE_REU_QUERY)) {
			stmt.execute();
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}

	public void criarTabelaDelegacia() {
		String CREATE_TABLE_DELEGACIA_QUERY = "CREATE TABLE IF NOT EXISTS delegacia(\n" +
				"id INT PRIMARY KEY,\n" +
				"id_ocorrencia INT,\n" +
				"id_reu INT,\n" +
				"FOREIGN KEY (id_ocorrencia) REFERENCES ocorrencia(id_ocorrencia),\n" +
				"FOREIGN KEY (id_reu) REFERENCES reu(id_reu)\n" +
				");";

		try (PreparedStatement stmt = connection.prepareStatement(CREATE_TABLE_DELEGACIA_QUERY)) {
			stmt.execute();
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}

	private void handleSQLException(SQLException e) {
		// Tratamento personalizado da exceção, por exemplo, registro de log ou relançamento como uma exceção personalizada.
		throw new RuntimeException("Erro ao executar consulta SQL", e);
	}
}
