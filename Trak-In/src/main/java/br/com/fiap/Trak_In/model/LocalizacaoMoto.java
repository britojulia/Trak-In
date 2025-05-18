package br.com.fiap.Trak_In.model;

import java.time.LocalDateTime;

import br.com.fiap.Trak_In.model.TypesEnum.FonteDados;
import br.com.fiap.Trak_In.model.TypesEnum.StatusLocalizacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalizacaoMoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double coordenadaX;

    private Double coordenadaY;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private StatusLocalizacao status;

    @Enumerated(EnumType.STRING)
    private FonteDados fonteDados; //RFID, VisaoComputacional, Fusao, Manual

    private Double confiabilidade;

    @ManyToOne
    private Moto motoId;

    @ManyToOne
    @JoinColumn(name = "patio_id")
    private Patio patio;

}
