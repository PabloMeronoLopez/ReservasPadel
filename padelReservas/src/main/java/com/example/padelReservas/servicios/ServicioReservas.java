package com.example.padelReservas.servicios;

import com.example.padelReservas.modelo.Pista;
import com.example.padelReservas.modelo.Reserva;

import com.example.padelReservas.modelo.Turno;
import com.example.padelReservas.modelo.User;
import com.example.padelReservas.repositorios.RepositorioReservas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ServicioReservas {
    @Autowired
    RepositorioReservas repositorioReservas;
    public ArrayList<Reserva> findAll(){

        return repositorioReservas.findAll();
    }
    public Reserva findById(long id){
        return repositorioReservas.findById(id);
    }
    public ArrayList<Reserva> findByPistaAndFechaAndTurno(Pista pista, Date fecha, Turno turno){
        return  repositorioReservas.findByPistaAndFechaAndTurno(pista,fecha,turno);
    }
    public ArrayList<Reserva> findByPistaAndFecha(Pista pista, Date fecha){
        return  repositorioReservas.findByPistaAndFecha(pista,fecha);
    }
    public ArrayList<Reserva> findByTurnoAndFecha(Turno turno, LocalDate fecha){
        return  repositorioReservas.findByTurnoAndFecha(turno,fecha);
    }
    public ArrayList<Reserva> findByUsuario(User usuario){
        return  repositorioReservas.findByUsuario(usuario);
    }
    public ArrayList<Reserva> findByTurno(Turno turno) {
        return repositorioReservas.findByTurno(turno);
    }
    public Reserva save(Reserva reserva){return repositorioReservas.save(reserva);}
    public void delete(Reserva reserva){
        repositorioReservas.delete(reserva);
    }

}
