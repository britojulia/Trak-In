package br.com.fiap.Trak_In.mappings;

import br.com.fiap.Trak_In.DTOs.LocalizacaoDTO;
import br.com.fiap.Trak_In.model.LocalizacaoMoto;

public class LocalizacaoMapper {
    
      public static LocalizacaoDTO toDTO(LocalizacaoMoto entity) {
        return LocalizacaoDTO.builder()
                .id(entity.getId())
                .coordenadaX(entity.getCoordenadaX())
                .coordenadaY(entity.getCoordenadaY())
                .timestamp(entity.getTimestamp())
                .status(entity.getStatus())
                .fonteDados(entity.getFonteDados())
                .confiabilidade(entity.getConfiabilidade())
                .moto(entity.getMoto() != null ? entity.getMoto().getPlaca() : null)
                .patioId(entity.getPatio() != null ? entity.getPatio().getId() : null)
                .build();
    }
}
