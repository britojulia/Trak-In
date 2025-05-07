package br.com.fiap.Trak_In.model;

import java.time.LocalDateTime;

import br.com.fiap.Trak_In.model.TypesEnum.FonteEvento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class EventoMoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; //tipo de evento - entrada, saída, manutenção etc

    private LocalDateTime timesTamp;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private FonteEvento fonteEvento; //Sistema, Manual, VisaoComputacional, RFID

    @ManyToOne
    private Usuario usuarioId;

    @ManyToOne
    private Moto motoId;
}
