package com.example.padelReservas.servicios;

import com.example.padelReservas.modelo.Pista;
import com.example.padelReservas.repositorios.RepositorioPistas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioPistas {
    @Autowired
    RepositorioPistas repositorioPistas;

    public ArrayList<Pista> findAll(){return repositorioPistas.findAll();}
    public Pista findById(long id){
        return repositorioPistas.findById(id);
    }
    public Pista findByNombre(String nombre){
        return repositorioPistas.findByNombre(nombre);
    }

}
