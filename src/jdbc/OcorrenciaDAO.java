package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OcorrenciaDAO {

    private Connection connection;

    public OcorrenciaDAO() throws ClassNotFoundException {
        this.connection = new Conexao().getConnection();
    }

    public void criarOcorrencia(Ocorrencia ocorrencia) {
        String INSERT_QUERY = "INSERT INTO ocorrencia (data, hora, local, tipoCrime, idDelegado) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY);

            stmt.setString(1, ocorrencia.getData());
            stmt.setString(2, ocorrencia.getHora());
            stmt.setString(3, ocorrencia.getLocal());
            stmt.setString(4, ocorrencia.getTipoCrime());
            stmt.setInt(5, ocorrencia.getIdDelegado());
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ocorrencia buscarPorId(int id) {
        String SELECT_BY_ID_QUERY = "SELECT * FROM reu WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID_QUERY);
            stmt.setInt(1, id);
            ResultSet resp = stmt.executeQuery();
            Ocorrencia ocorrencia = new Ocorrencia();
            while(resp.next()) {
                ocorrencia.setId(resp.getInt("id"));
                ocorrencia.setData(resp.getString("data"));
                ocorrencia.setHora(resp.getString("hora"));
                ocorrencia.setLocal(resp.getString("local"));
                ocorrencia.setTipoCrime(resp.getString("tipocrime"));
                ocorrencia.setIdDelegado(resp.getInt("iddelegado"));
            }
            return ocorrencia;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ocorrencia> buscarTodos() {
        String SELECT_ALL_QUERY = "SELECT * from ocorrencia";

        try {
            List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resp = stmt.executeQuery();

            while(resp.next()) {
                Ocorrencia ocorrencia = new Ocorrencia(resp.getInt("id"), resp.getString("data"), resp.getString("hora"), resp.getString("local"), resp.getString("tipoCrime"), resp.getInt("idDelegado"));
                ocorrencias.add(ocorrencia);
            }

            resp.close();
            stmt.close();

            return ocorrencias;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Ocorrencia ocorrencia) {
        String UPDATE_QUERY = "UPDATE ocorrencia SET data = ?, hora = ?, local = ?, tipoCrime = ?, idDelegado = ?, WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY);
            stmt.setString(1, ocorrencia.getData());
            stmt.setString(2, ocorrencia.getHora());
            stmt.setString(3, ocorrencia.getLocal());
            stmt.setString(4, ocorrencia.getTipoCrime());
            stmt.setInt(5, ocorrencia.getIdDelegado());
            stmt.setInt(6, ocorrencia.getId());

            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) {
        String DELETE_QUERY = "DELETE from ocorrencia WHERE id = ?";

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
