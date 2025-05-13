package br.com.fiap.Trak_In.model;

import java.util.List;

import br.com.fiap.Trak_In.model.TypesEnum.StatusCamera;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String posicao; //e.g., "Nordeste", "Entrada"

    private Double posicaoX; //coordenada X no pátio

    private Double posicaoY; //coordenada Y no pátio
    
    private Double altura; //ltura de instalação em metros
    
    private Double anguloVisao; //ângulo de visão em graus

    @Enumerated(EnumType.STRING)
    private StatusCamera status;

    private String url; //url stream de video

    @ManyToOne
    private Patio patioId;


    @OneToMany(mappedBy = "camera")
    private List<DeteccaoVisual> deteccoes;

}
