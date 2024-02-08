package com.example.padelReservas.servicios;

import com.example.padelReservas.modelo.Turno;
import com.example.padelReservas.repositorios.RepositorioTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;

@Service
public class ServicioTurno {
    @Autowired
    RepositorioTurno repositorioTurno;
    public ArrayList<Turno> findAll(){return  repositorioTurno.findAll();}
    public Turno findById(long id){ return  repositorioTurno.findById(id);}
    public Turno findByHoraInicio(Time hora){
        return  repositorioTurno.findByHoraInicio(hora);
    }
    public  Turno findByTurno(String turno){return repositorioTurno.findByTurno(turno);}
}
