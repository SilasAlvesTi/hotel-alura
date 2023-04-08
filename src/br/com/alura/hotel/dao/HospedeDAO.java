package br.com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.hotel.models.Hospede;

public class HospedeDAO {

    private Connection connection;

    public HospedeDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<Hospede> listar() {
        List<Hospede> hospedes = new ArrayList<>();
        String sql = "SELECT * FROM HOSPEDE";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            trasformarResultSetEmReserva(hospedes, pstm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hospedes;
    }

    private void trasformarResultSetEmReserva(List<Hospede> hospedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Hospede hospede = new Hospede(
                    rst.getInt(1), 
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getString(4), 
                    rst.getString(5),
                    rst.getString(6),
                    rst.getInt(7)
                );

				hospedes.add(hospede);
			}
		}
	}
}