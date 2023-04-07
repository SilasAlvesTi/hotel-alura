package br.com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.hotel.models.Reserva;

public class ReservaDAO {
    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM RESERVA";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            trasformarResultSetEmReserva(reservas, pstm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }


    public List<Reserva> listarPeloId(Integer id) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM RESERVA WHERE Id = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();

            trasformarResultSetEmReserva(reservas, pstm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    public void reservar(String dataEntrada, String dataSaida, float valor, String formaPagamento){
        String sql = "INSERT INTO RESERVA (DataEntrada, DataSaida, Valor, FormaPagamento) "+
                "VALUES (?, ?, ?, ?);";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dataEntrada);
            pstm.setString(2, dataSaida);
            pstm.setFloat(3, valor);
            pstm.setString(4, formaPagamento);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva reserva = new Reserva(
                    rst.getInt(1), 
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getFloat(4), 
                    rst.getString(5)
                );

				reservas.add(reserva);
			}
		}
	}
}
