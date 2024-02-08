package com.example.padelReservas.repositorios;

import com.example.padelReservas.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface RepositorioReservas extends JpaRepository<Reserva, Long> {
    public ArrayList<Reserva> findAll();
    public Reserva findById(long id);
    //findByDateContains

    public ArrayList<Reserva> findByPistaAndFecha(Pista pista, Date fecha);
    public ArrayList<Reserva> findByTurnoAndFecha(Turno turno, LocalDate fecha);
    public ArrayList<Reserva> findByPistaAndFechaAndTurno(Pista pista, Date fecha, Turno turno);
    public Reserva save(Reserva reserva);
    public void delete(Reserva reserva);
    public  ArrayList<Reserva> findByTurno(Turno turno);
    public  ArrayList<Reserva> findByUsuario(User usuario);

}
