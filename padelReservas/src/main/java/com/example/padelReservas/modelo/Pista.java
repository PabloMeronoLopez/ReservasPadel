package com.example.padelReservas.modelo;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="pistas")
public class Pista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPista;
    private String nombre;
}
