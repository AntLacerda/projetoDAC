package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DelegadoDAO {
    private Connection connection;

    public DelegadoDAO() throws ClassNotFoundException {
        this.connection = new Conexao().getConnection();
    }

    public void inserirDelegado(Delegado delegado) {
        String INSERT_QUERY = "INSERT INTO delegado (matricula, nome, contato) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY);

            stmt.setString(1, delegado.getMatricula());
            stmt.setString(2, delegado.getNome());
            stmt.setString(3, delegado.getContato());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Delegado buscarPorId(int id) {
        String SELECT_BY_ID_QUERY = "SELECT * FROM delegado WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID_QUERY);
            stmt.setInt(1, id);
            ResultSet resp = stmt.executeQuery();
            Delegado delegado = new Delegado();
            while (resp.next()) {
                delegado.setId(resp.getInt("id"));
                delegado.setMatricula(resp.getString("matricula"));
                delegado.setNome(resp.getString("nome"));
                delegado.setContato(resp.getString("contato"));
            }
            return delegado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Delegado> buscarTodos() {
        String SELECT_ALL_QUERY = "SELECT * FROM delegado";

        try {
            List<Delegado> delegados = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resp = stmt.executeQuery();

            while (resp.next()) {
                Delegado delegado = new Delegado(resp.getInt("id"), resp.getString("matricula"),
                        resp.getString("nome"), resp.getString("contato"));
                delegados.add(delegado);
            }

            resp.close();
            stmt.close();

            return delegados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Delegado delegado) {
        String UPDATE_QUERY = "UPDATE delegado SET matricula = ?, nome = ?, contato = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY);
            stmt.setString(1, delegado.getMatricula());
            stmt.setString(2, delegado.getNome());
            stmt.setString(3, delegado.getContato());
            stmt.setInt(4, delegado.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) {
        String DELETE_QUERY = "DELETE FROM delegado WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
