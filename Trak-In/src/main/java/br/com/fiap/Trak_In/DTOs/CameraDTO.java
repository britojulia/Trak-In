package br.com.fiap.Trak_In.DTOs;
import br.com.fiap.Trak_In.model.TypesEnum.StatusCamera;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CameraDTO {

    private Long id;
    private String posicao;
    private Double posicaoX;
    private Double posicaoY;
    private Double altura;
    private Double anguloVisao;
    private StatusCamera status;
    private String url;
    private Long patioId; //retornando apenas o id do pátio
}

