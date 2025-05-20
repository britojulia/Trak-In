package br.com.fiap.Trak_In.DTOs;

import br.com.fiap.Trak_In.model.TypesEnum.PerfilUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private PerfilUsuario perfil;

    private Long filialId;//retornando apenas o id da filial
}
