package br.com.fiap.Trak_In.DTOs;

import br.com.fiap.Trak_In.model.Camera;

public class CameraMapper {
    public static CameraDTO toDTO(Camera camera) {

        return CameraDTO.builder()
            .id(camera.getId())
            .posicao(camera.getPosicao())
            .posicaoX(camera.getPosicaoX())
            .posicaoY(camera.getPosicaoY())
            .altura(camera.getAltura())
            .anguloVisao(camera.getAnguloVisao())
            .status(camera.getStatus())
            .url(camera.getUrl())
            .patioId(camera.getPatio() != null ? camera.getPatio().getId() : null)
            .build();
    }
}


