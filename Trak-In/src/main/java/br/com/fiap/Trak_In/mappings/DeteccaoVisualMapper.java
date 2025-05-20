package br.com.fiap.Trak_In.mappings;

import br.com.fiap.Trak_In.DTOs.DeteccaoVisualDTO;
import br.com.fiap.Trak_In.model.DeteccaoVisual;

public class DeteccaoVisualMapper {
    
     public static DeteccaoVisualDTO toDTO(DeteccaoVisual entity) {
        return DeteccaoVisualDTO.builder()
                .id(entity.getId())
                .timesTamp(entity.getTimesTamp())
                .coordenadaXImagem(entity.getCoordenadaXImagem())
                .coordenadaYImagem(entity.getCoordenadaYImagem())
                .coordenadaXPatio(entity.getCoordenadaXPatio())
                .coordenadaYPatio(entity.getCoordenadaYPatio())
                .imagemCaptura(entity.getImagemCaptura())
                .motoId(entity.getMoto() != null ? entity.getMoto().getId() : null)
                .cameraId(entity.getCamera() != null ? entity.getCamera().getId() : null)
                .build();
    }

}
