package br.com.fiap.Trak_In.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilialDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    private Long responsavelId; //retornando apenas o id do responsável pela filial
    private Long patioId;       // //retornando apenas o id do pátio
}
