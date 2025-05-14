package br.com.fiap.Trak_In.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class DeteccaoVisual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timesTamp;

    private Double coordenadaXImagem;

    private Double coordenadaYImagem;

    private Double coordenadaXPatio;

    private Double coordenadaYPatio;

    private String imagemCaptura; //opcional, URL para imagem capturada

    @ManyToOne
    private Moto moto;

    @ManyToOne
    private Camera cameraId;

}
