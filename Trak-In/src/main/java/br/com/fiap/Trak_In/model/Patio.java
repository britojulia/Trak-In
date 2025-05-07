package br.com.fiap.Trak_In.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    private String cidade;
    
    private String estado;

    @NotBlank
    private String pais; //brasil ou mexico

    private Double dimensaoX; //largura em metros
    
    private Double dimensaoY; //cumprimento em metros

    private String plantaBaixa; //url para imagem da planta

    @OneToMany(mappedBy = "patio")
    private List<Camera> cameras;

    @OneToMany(mappedBy = "patio")
    private List<ZonaPatio> zonas;

    @OneToMany(mappedBy = "patio")
    private List<LocalizacaoMoto> localizacoes;

    @OneToOne(mappedBy = "patio")
    private Filial filialId;
}
