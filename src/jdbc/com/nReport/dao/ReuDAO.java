package jdbc.com.nReport.dao;

import jdbc.com.nReport.entities.Reu;
import jdbc.com.nReport.conexao.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReuDAO {
	
	private Connection connection;
	
	public ReuDAO() throws ClassNotFoundException {
		this.connection = new Conexao().getConnection();
	}
	
	public void inserirReu(Reu reu) {
		String INSERT_QUERY = "INSERT INTO reu (nome, cpf, nascimento) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY);
			
			stmt.setString(1, reu.getNome());
			stmt.setString(2, reu.getCpf());
			stmt.setString(3, reu.getNascimento());
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Reu buscarPorId(int id) {
		String SELECT_BY_ID_QUERY = "SELECT * FROM reu WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID_QUERY);
			stmt.setInt(1, id);
			ResultSet resp = stmt.executeQuery(); 
			Reu reu = new Reu();
			while(resp.next()) {
				reu.setId(resp.getInt("id"));
				reu.setNome(resp.getString("nome"));
				reu.setCpf(resp.getString("cpf"));
				reu.setNascimento(resp.getString("nascimento"));
			}
			return reu;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reu> buscarTodos() {
		String SELECT_ALL_QUERY = "SELECT * from reu";
		
		try {
			List<Reu> reus = new ArrayList<Reu>();
			PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_QUERY);
			ResultSet resp = stmt.executeQuery();
			
			while(resp.next()) {
				Reu reu = new Reu(resp.getInt("id"), resp.getString("nome"), resp.getString("cpf"), resp.getString("nascimento"));
				reus.add(reu);
			}
			
			resp.close();
			stmt.close();
			
			return reus;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(Reu reu) {
		String UPDATE_QUERY = "UPDATE reu SET nome = ?, cpf = ?, nascimento = ? WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY);
			stmt.setString(1, reu.getNome());
			stmt.setString(2, reu.getCpf());
			stmt.setString(3, reu.getNascimento());
			stmt.setInt(4, reu.getId());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(int id) {
		String DELETE_QUERY = "DELETE from reu WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
