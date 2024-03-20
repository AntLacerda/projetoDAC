package jdbc.com.nReport.dao;

import jdbc.com.nReport.entities.Delegacia;
import jdbc.com.nReport.conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class DelegaciaDAO {
    private Connection connection;

    public DelegaciaDAO() throws ClassNotFoundException {
        this.connection = new Conexao().getConnection();
    }

    public void salvar(Delegacia delegacia){
        String INSERT_QUERY = "INSERT INTO delegacia (idocorrencia, idreu) VALUES (?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY);

            stmt.setInt(1, delegacia.getOcorrencia().getId());
            stmt.setInt(2, delegacia.getReu().getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
