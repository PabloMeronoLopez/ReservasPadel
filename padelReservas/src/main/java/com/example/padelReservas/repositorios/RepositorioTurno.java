package com.example.padelReservas.repositorios;

import com.example.padelReservas.modelo.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.ArrayList;

@Repository
public interface RepositorioTurno extends JpaRepository<Turno, Long> {
    public ArrayList<Turno> findAll();
    public  Turno findByTurno(String turno);
    public Turno findById(long id);
    public Turno findByHoraInicio(Time horaInicio);
}
