package br.com.alura.hotel.controllers;

import java.util.List;
import java.sql.Connection;

import br.com.alura.hotel.dao.HospedeDAO;
import br.com.alura.hotel.factory.ConnectionFactory;
import br.com.alura.hotel.models.Hospede;

public class HospedeController {
    
    private HospedeDAO hospedeDAO;

    public HospedeController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.hospedeDAO = new HospedeDAO(connection);
    }

    public List<Hospede> listar() {
        List<Hospede> hospedes =  this.hospedeDAO.listar();
        return hospedes;
    }
}
