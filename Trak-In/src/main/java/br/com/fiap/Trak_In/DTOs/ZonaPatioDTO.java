package br.com.fiap.Trak_In.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZonaPatioDTO {
    private Long id;
    private String nome;
    private Double coordenadaInicialX;
    private Double coordenadaInicialY;
    private Double coordenadaFinalX;
    private Double coordenadaFinalY;
    private String cor;

    private Long patioId; //retornando apenas o id do patio
}
