package br.com.fiap.Trak_In.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JoinColumn(name = "patio_id")
    private Patio patio;
}
