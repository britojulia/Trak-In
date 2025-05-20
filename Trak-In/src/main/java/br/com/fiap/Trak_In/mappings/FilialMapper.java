package br.com.fiap.Trak_In.mappings;

import br.com.fiap.Trak_In.DTOs.FilialDTO;
import br.com.fiap.Trak_In.model.Filial;

public class FilialMapper {
    
     public static FilialDTO toDTO(Filial entity) {
        return FilialDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .telefone(entity.getTelefone())
                .email(entity.getEmail())
                .responsavelId(entity.getResponsavelId() != null ? entity.getResponsavelId().getId() : null)
                .patioId(entity.getPatio() != null ? entity.getPatio().getId() : null)
                .build();
    }
}
