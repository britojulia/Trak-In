package br.com.fiap.Trak_In.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class ZonaPatio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; //Manutenção, Estacionamento, Lavagem, etc

    private Double coordenadaInicialX;

    private Double coordenadaInicialY;

    private Double coordenadaFinalX;

    private Double coordenadaFinalY;

    private String cor; //representação visual por cor

    @ManyToOne
    private Patio patio;
}
