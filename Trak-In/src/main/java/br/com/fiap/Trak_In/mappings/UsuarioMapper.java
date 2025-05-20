package br.com.fiap.Trak_In.mappings;

import br.com.fiap.Trak_In.DTOs.UsuarioDTO;
import br.com.fiap.Trak_In.model.Usuario;

public class UsuarioMapper {
    
      public static UsuarioDTO toDTO(Usuario entity) {
        return UsuarioDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .perfil(entity.getPerfil())
                .filialId(entity.getFilialId() != null ? entity.getFilialId().getId() : null)
                .build();
    }
}
