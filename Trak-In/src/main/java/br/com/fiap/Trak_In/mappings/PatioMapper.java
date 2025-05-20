package br.com.fiap.Trak_In.mappings;

import br.com.fiap.Trak_In.DTOs.PatioDTO;
import br.com.fiap.Trak_In.model.Patio;

public class PatioMapper {
    
    public static PatioDTO toDTO(Patio entity) {
        return PatioDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .endereco(entity.getEndereco())
                .cidade(entity.getCidade())
                .estado(entity.getEstado())
                .pais(entity.getPais())
                .dimensaoX(entity.getDimensaoX())
                .dimensaoY(entity.getDimensaoY())
                .plantaBaixa(entity.getPlantaBaixa())
                .filialId(entity.getFilialId() != null ? entity.getFilialId().getId() : null)
                .build();
    }
}
