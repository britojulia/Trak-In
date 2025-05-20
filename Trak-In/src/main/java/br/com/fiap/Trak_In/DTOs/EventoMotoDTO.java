package br.com.fiap.Trak_In.DTOs;

import java.time.LocalDateTime;

import br.com.fiap.Trak_In.model.TypesEnum.FonteEvento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoMotoDTO {
    private Long id;
    private String tipo;
    private LocalDateTime timesTamp;
    private String observacao;
    private FonteEvento fonteEvento;

    private Long usuarioId;  //retornando apenas o id do usuario referenciado a moto
    private Long motoId;  //retornando apenas o id da moto
}
