package br.com.fiap.Trak_In.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatioDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String cidade;
    private String estado;
    private String pais;
    private Double dimensaoX;
    private Double dimensaoY;
    private String plantaBaixa;

    private List<Long> cameraIds; //retornando lista de id das cameras referenciadas
    private List<Long> zonaPatioIds; //retornando lista de id das zonas dos patios referenciadas
    private List<Long> localizacaoMotoIds; //retornando lista de id da localizações das motos 
    private Long filialId; //retornando apenas o id da filial
}
