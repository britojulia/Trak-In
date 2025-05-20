package br.com.fiap.Trak_In.mappings;

import br.com.fiap.Trak_In.DTOs.ZonaPatioDTO;
import br.com.fiap.Trak_In.model.ZonaPatio;

public class ZonaPatioMApper {
    
     public static ZonaPatioDTO toDTO(ZonaPatio entity) {
        return ZonaPatioDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .coordenadaInicialX(entity.getCoordenadaInicialX())
                .coordenadaInicialY(entity.getCoordenadaInicialY())
                .coordenadaFinalX(entity.getCoordenadaFinalX())
                .coordenadaFinalY(entity.getCoordenadaFinalY())
                .cor(entity.getCor())
                .patioId(entity.getPatio() != null ? entity.getPatio().getId() : null)
                .build();
    }
}
