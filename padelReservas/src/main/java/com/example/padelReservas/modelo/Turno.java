package com.example.padelReservas.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
@Table(name="turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTurno;
    private String turno;
    private Time horaInicio;
    private Time horaFinal;
}
