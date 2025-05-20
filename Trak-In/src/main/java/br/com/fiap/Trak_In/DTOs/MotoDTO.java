package br.com.fiap.Trak_In.DTOs;

import java.time.LocalDate;

import br.com.fiap.Trak_In.model.TypesEnum.StatusMoto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MotoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private Integer ano;
    private StatusMoto status;
    private String rfidTag;
    private LocalDate dataAquisicao;
    private LocalDate ultimaManutencao;
    private String imagemReferencia;
    private String caracteristicasVisuais;

    private Long patioId; //retornando apenas o id do pátio
}
