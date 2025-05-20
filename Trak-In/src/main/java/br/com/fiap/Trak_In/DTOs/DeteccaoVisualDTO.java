package br.com.fiap.Trak_In.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeteccaoVisualDTO {
    private Long id;
    private LocalDateTime timesTamp;
    private Double coordenadaXImagem;
    private Double coordenadaYImagem;
    private Double coordenadaXPatio;
    private Double coordenadaYPatio;
    private String imagemCaptura;

    private Long motoId;  //retornando apenas o id da moto
    private Long cameraId;  //retornando apenas o id da camera
}
