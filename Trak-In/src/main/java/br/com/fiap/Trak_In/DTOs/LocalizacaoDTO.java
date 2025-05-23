package br.com.fiap.Trak_In.DTOs;

import java.time.LocalDateTime;

import br.com.fiap.Trak_In.model.TypesEnum.FonteDados;
import br.com.fiap.Trak_In.model.TypesEnum.StatusLocalizacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LocalizacaoDTO {
    private Long id;
    private Double coordenadaX;
    private Double coordenadaY;
    private LocalDateTime timestamp;
    private StatusLocalizacao status;
    private FonteDados fonteDados;
    private Double confiabilidade;

    private String moto; //retornando apenas a placa da moto
    private Long patioId; //retornando apenas o id do pátio
}
