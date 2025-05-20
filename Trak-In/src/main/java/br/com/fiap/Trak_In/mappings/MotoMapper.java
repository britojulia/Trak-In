package br.com.fiap.Trak_In.mappings;

import br.com.fiap.Trak_In.DTOs.MotoDTO;
import br.com.fiap.Trak_In.model.Moto;

public class MotoMapper {
    
    public static MotoDTO toDTO(Moto entity) {
        return MotoDTO.builder()
                .id(entity.getId())
                .placa(entity.getPlaca())
                .modelo(entity.getModelo())
                .ano(entity.getAno())
                .status(entity.getStatus())
                .rfidTag(entity.getRfidTag())
                .dataAquisicao(entity.getDataAquisicao())
                .ultimaManutencao(entity.getUltimaManutencao())
                .imagemReferencia(entity.getImagemReferencia())
                .caracteristicasVisuais(entity.getCaracteristicasVisuais())
                .patioId(entity.getPatioId() != null ? entity.getPatioId().getId() : null)
                .build();
    }
}
