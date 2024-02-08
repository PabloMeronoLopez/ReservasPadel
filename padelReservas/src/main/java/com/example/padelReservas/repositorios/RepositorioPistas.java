package com.example.padelReservas.repositorios;

import com.example.padelReservas.modelo.Pista;
import com.example.padelReservas.modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RepositorioPistas extends JpaRepository<Pista, Long> {
    public ArrayList<Pista> findAll();
    public Pista findById(long id);
    public Pista findByNombre(String Nombre);


}
