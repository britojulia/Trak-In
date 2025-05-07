package br.com.fiap.Trak_In.model;

import java.time.LocalDate;

import br.com.fiap.Trak_In.model.TypesEnum.StatusMoto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String placa;

    @NotBlank
    private String modelo;

    private Integer ano;

    @Enumerated(EnumType.STRING)
    private StatusMoto status;

    @NotBlank
    private String rfidTag;

    private LocalDate dataAquisicao;

    private LocalDate ultimaManutencao;

    private String imagemReferencia;

    @Lob
    private String caracteristicasVisuais; //JSON da foto será como string

    @ManyToOne
    private Patio patioId;
    
}

