package br.com.fiap.Trak_In.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.Trak_In.model.Camera;
import br.com.fiap.Trak_In.model.DeteccaoVisual;
import br.com.fiap.Trak_In.model.EventoMoto;
import br.com.fiap.Trak_In.model.Filial;
import br.com.fiap.Trak_In.model.LocalizacaoMoto;
import br.com.fiap.Trak_In.model.Moto;
import br.com.fiap.Trak_In.model.Patio;
import br.com.fiap.Trak_In.model.TypesEnum.FonteDados;
import br.com.fiap.Trak_In.model.TypesEnum.FonteEvento;
import br.com.fiap.Trak_In.model.TypesEnum.PerfilUsuario;
import br.com.fiap.Trak_In.model.TypesEnum.StatusCamera;
import br.com.fiap.Trak_In.model.TypesEnum.StatusLocalizacao;
import br.com.fiap.Trak_In.model.TypesEnum.StatusMoto;
import br.com.fiap.Trak_In.model.Usuario;
import br.com.fiap.Trak_In.model.ZonaPatio;
import br.com.fiap.Trak_In.repository.CameraRepository;
import br.com.fiap.Trak_In.repository.DeteccaoVisualRepository;
import br.com.fiap.Trak_In.repository.EventoMotoRepository;
import br.com.fiap.Trak_In.repository.FilialRepository;
import br.com.fiap.Trak_In.repository.LocalizacaoRepository;
import br.com.fiap.Trak_In.repository.MotoRepository;
import br.com.fiap.Trak_In.repository.PatioRepository;
import br.com.fiap.Trak_In.repository.UsuarioRepository;
import br.com.fiap.Trak_In.repository.ZonaPatioRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DataBaseSeeder {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private DeteccaoVisualRepository deteccaoVisualRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private ZonaPatioRepository zonaPatioRepository;

     @Autowired
    private EventoMotoRepository eventoMotoRepository;

    @PostConstruct
    public void init() {
    Patio patio = Patio.builder()
        .nome("Patio Central")
        .endereco("Rua Central, 123")
        .cidade("São Paulo")
        .estado("SP")
        .pais("Brasil")
        .dimensaoX(100.0)
        .dimensaoY(200.0)
        .plantaBaixa("https://example.com/planta_patio.png")
        .build();
    patioRepository.save(patio);

    Filial filial = Filial.builder()
        .nome("Filial Central")
        .telefone("11 1234-5678")
        .email("central@trak.in")
        .patio(patio)
        .build();
    filialRepository.save(filial);

    Camera camera1 = Camera.builder()
        .posicao("Entrada")
        .posicaoX(10.0)
        .posicaoY(20.0)
        .altura(5.0)
        .anguloVisao(90.0)
        .status(StatusCamera.ATIVO)
        .url("https://streaming.camera1.com")
        .patio(patio)
        .build();

        cameraRepository.save(camera1);

    Camera camera2 = Camera.builder()
        .posicao("Estacionamento")
        .posicaoX(50.0)
        .posicaoY(40.0)
        .altura(6.0)
        .anguloVisao(120.0)
        .status(StatusCamera.ATIVO)
        .url("https://streaming.camera2.com")
        .patio(patio)
        .build();

        cameraRepository.save(camera2);

    // Cria 10 usuários
    List<Usuario> usuarios = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
        PerfilUsuario perfil = (i == 1) ? PerfilUsuario.ADMIN : PerfilUsuario.OPERADOR;
        usuarios.add(Usuario.builder()
            .nome("Usuario " + i)
            .email("usuario" + i + "@trak.in")
            .senha("senha" + i)
            .perfil(perfil)
            .filialId(filial)
            .build());
    }
    usuarioRepository.saveAll(usuarios);

    // Cria 10 motos
    List<Moto> motos = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
        motos.add(Moto.builder()
            .placa("MOTO" + String.format("%04d", i))  // ex: MOTO0001
            .modelo("Modelo " + i)
            .ano(2015 + i)
            .status(StatusMoto.DISPONIVEL)
            .rfidTag("RFID" + (1000000 + i))
            .dataAquisicao(LocalDate.of(2015 + i, 1, 15))
            .ultimaManutencao(LocalDate.of(2024, 4, i))
            .imagemReferencia("https://example.com/moto" + i + ".png")
            .caracteristicasVisuais("{\"cor\":\"cor" + i + "\",\"tipo\":\"tipo" + i + "\"}")
            .patioId(patio)
            .build());
    }
    motoRepository.saveAll(motos);

    // Cria 10 detecções visuais
    List<DeteccaoVisual> deteccoes = new ArrayList<>();
    LocalDateTime baseTime = LocalDateTime.now().minusDays(1);
    for (int i = 0; i < 10; i++) {
        deteccoes.add(DeteccaoVisual.builder()
            .timesTamp(baseTime.plusMinutes(i * 15))
            .coordenadaXImagem(100.0 + i * 5)
            .coordenadaYImagem(150.0 + i * 3)
            .coordenadaXPatio(10.0 + i)
            .coordenadaYPatio(20.0 + i)
            .imagemCaptura("https://example.com/captura" + (i + 1) + ".jpg")
            .moto(motos.get(i))
            .camera((i % 2 == 0) ? camera1 : camera2)
            .build());
    }
    deteccaoVisualRepository.saveAll(deteccoes);

    
    List<ZonaPatio> zonas = List.of(
        ZonaPatio.builder()
            .nome("Manutenção")
            .coordenadaInicialX(0.0)
            .coordenadaInicialY(0.0)
            .coordenadaFinalX(30.0)
            .coordenadaFinalY(50.0)
            .cor("#FF0000") // vermelho
            .patio(patio)
            .build(),
        ZonaPatio.builder()
            .nome("Estacionamento")
            .coordenadaInicialX(31.0)
            .coordenadaInicialY(0.0)
            .coordenadaFinalX(70.0)
            .coordenadaFinalY(100.0)
            .cor("#00FF00") // verde
            .patio(patio)
            .build(),
        ZonaPatio.builder()
            .nome("Lavagem")
            .coordenadaInicialX(71.0)
            .coordenadaInicialY(0.0)
            .coordenadaFinalX(100.0)
            .coordenadaFinalY(50.0)
            .cor("#0000FF") // azul
            .patio(patio)
            .build()
    );
    zonaPatioRepository.saveAll(zonas);

    //cria 10 localizacoes pras motos
    List<LocalizacaoMoto> localizacoes = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
        localizacoes.add(LocalizacaoMoto.builder()
            .coordenadaX(10.0 + i * 3)
            .coordenadaY(20.0 + i * 2)
            .timestamp(baseTime.plusMinutes(i * 10))
            .status(StatusLocalizacao.ESTACIONADA)
            .fonteDados(FonteDados.VISAO_COMPUTACIONAL)
            .confiabilidade(0.85 + i * 0.01)
            .motoId(motos.get(i))
            .patio(patio)
            .build());
    }
    localizacaoRepository.saveAll(localizacoes);


    List<EventoMoto> eventos = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
        eventos.add(EventoMoto.builder()
            .tipo((i % 2 == 0) ? "Entrada" : "Saída")
            .timesTamp(baseTime.plusMinutes(i * 20))
            .observacao("Evento automático " + (i + 1))
            .fonteEvento(FonteEvento.SISTEMA)
            .usuarioId(usuarios.get(i))
            .motoId(motos.get(i))
            .build());
    }
    eventoMotoRepository.saveAll(eventos); 

}

    
}
