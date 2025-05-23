package br.com.fiap.Trak_In.mappings;

import br.com.fiap.Trak_In.DTOs.EventoMotoDTO;
import br.com.fiap.Trak_In.model.EventoMoto;

public class EventoMotoMapper {
    
       public static EventoMotoDTO toDTO(EventoMoto entity) {
        return EventoMotoDTO.builder()
                .id(entity.getId())
                .tipo(entity.getTipo())
                .timesTamp(entity.getTimesTamp())
                .observacao(entity.getObservacao())
                .fonteEvento(entity.getFonteEvento())
                .usuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .placaMoto(entity.getMoto() != null ? entity.getMoto().getPlaca() : null)
                .build();
    }
}
