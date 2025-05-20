package br.com.fiap.Trak_In.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    var usuarios =List.of(
        Usuario.builder().nome("João Silva").email("joao@trak.in").senha("123456").perfil(PerfilUsuario.ADMIN).filialId(filial).build(),
        Usuario.builder().nome("Maria Santos").email("maria@trak.in").senha("123456").perfil(PerfilUsuario.OPERADOR).filialId(filial).build(),
        Usuario.builder().nome("Carlos Lima").email("carlos@trak.in").senha("123456").perfil(PerfilUsuario.OPERADOR).filialId(filial).build(),
        Usuario.builder().nome("Ana Paula").email("ana@trak.in").senha("123456").perfil(PerfilUsuario.OPERADOR).filialId(filial).build(),
        Usuario.builder().nome("Lucas Rocha").email("lucas@trak.in").senha("123456").perfil(PerfilUsuario.OPERADOR).filialId(filial).build(),
        Usuario.builder().nome("Fernanda Melo").email("fernanda@trak.in").senha("123456").perfil(PerfilUsuario.OPERADOR).filialId(filial).build(),
        Usuario.builder().nome("Ricardo Alves").email("ricardo@trak.in").senha("123456").perfil(PerfilUsuario.OPERADOR).filialId(filial).build(),
        Usuario.builder().nome("Juliana Costa").email("juliana@trak.in").senha("123456").perfil(PerfilUsuario.OPERADOR).filialId(filial).build(),
        Usuario.builder().nome("Bruno Teixeira").email("bruno@trak.in").senha("123456").perfil(PerfilUsuario.OPERADOR).filialId(filial).build(),
        Usuario.builder().nome("Patrícia Oliveira").email("patricia@trak.in").senha("123456").perfil(PerfilUsuario.OPERADOR).filialId(filial).build()
    );
    usuarioRepository.saveAll(usuarios);

    // Cria 10 motos
    var motos = List.of(
        Moto.builder().placa("ABC1A01").modelo("Honda CG 160").ano(2019).status(StatusMoto.DISPONIVEL).rfidTag("RFID0001").dataAquisicao(LocalDate.of(2020, 1, 10)).ultimaManutencao(LocalDate.of(2024, 5, 1)).imagemReferencia("https://exemplo.com/motos/1.png").caracteristicasVisuais("{\"cor\":\"vermelha\",\"tipo\":\"esportiva\"}").patioId(patio).build(),
        Moto.builder().placa("DEF2B02").modelo("Yamaha Fazer").ano(2020).status(StatusMoto.DISPONIVEL).rfidTag("RFID0002").dataAquisicao(LocalDate.of(2021, 2, 15)).ultimaManutencao(LocalDate.of(2024, 5, 2)).imagemReferencia("https://exemplo.com/motos/2.png").caracteristicasVisuais("{\"cor\":\"preta\",\"tipo\":\"naked\"}").patioId(patio).build(),
        Moto.builder().placa("GHI3C03").modelo("Suzuki Yes").ano(2018).status(StatusMoto.EM_MANUTENCAO).rfidTag("RFID0003").dataAquisicao(LocalDate.of(2019, 3, 20)).ultimaManutencao(LocalDate.of(2024, 4, 28)).imagemReferencia("https://exemplo.com/motos/3.png").caracteristicasVisuais("{\"cor\":\"azul\",\"tipo\":\"urbana\"}").patioId(patio).build(),
        Moto.builder().placa("JKL4D04").modelo("Dafra Apache").ano(2021).status(StatusMoto.DISPONIVEL).rfidTag("RFID0004").dataAquisicao(LocalDate.of(2022, 4, 5)).ultimaManutencao(LocalDate.of(2024, 5, 3)).imagemReferencia("https://exemplo.com/motos/4.png").caracteristicasVisuais("{\"cor\":\"branca\",\"tipo\":\"trail\"}").patioId(patio).build(),
        Moto.builder().placa("MNO5E05").modelo("Kawasaki Ninja").ano(2022).status(StatusMoto.ALUGADA).rfidTag("RFID0005").dataAquisicao(LocalDate.of(2022, 8, 18)).ultimaManutencao(LocalDate.of(2024, 5, 5)).imagemReferencia("https://exemplo.com/motos/5.png").caracteristicasVisuais("{\"cor\":\"verde\",\"tipo\":\"esportiva\"}").patioId(patio).build(),
        Moto.builder().placa("PQR6F06").modelo("BMW G310R").ano(2021).status(StatusMoto.DISPONIVEL).rfidTag("RFID0006").dataAquisicao(LocalDate.of(2021, 11, 12)).ultimaManutencao(LocalDate.of(2024, 5, 6)).imagemReferencia("https://exemplo.com/motos/6.png").caracteristicasVisuais("{\"cor\":\"cinza\",\"tipo\":\"naked\"}").patioId(patio).build(),
        Moto.builder().placa("STU7G07").modelo("Harley Davidson").ano(2017).status(StatusMoto.EM_MANUTENCAO).rfidTag("RFID0007").dataAquisicao(LocalDate.of(2018, 6, 23)).ultimaManutencao(LocalDate.of(2024, 4, 25)).imagemReferencia("https://exemplo.com/motos/7.png").caracteristicasVisuais("{\"cor\":\"preta\",\"tipo\":\"custom\"}").patioId(patio).build(),
        Moto.builder().placa("VWX8H08").modelo("Honda Biz").ano(2019).status(StatusMoto.DISPONIVEL).rfidTag("RFID0008").dataAquisicao(LocalDate.of(2020, 3, 30)).ultimaManutencao(LocalDate.of(2024, 5, 7)).imagemReferencia("https://exemplo.com/motos/8.png").caracteristicasVisuais("{\"cor\":\"rosa\",\"tipo\":\"scooter\"}").patioId(patio).build(),
        Moto.builder().placa("YZA9I09").modelo("Yamaha XTZ").ano(2020).status(StatusMoto.ALUGADA).rfidTag("RFID0009").dataAquisicao(LocalDate.of(2021, 5, 10)).ultimaManutencao(LocalDate.of(2024, 5, 8)).imagemReferencia("https://exemplo.com/motos/9.png").caracteristicasVisuais("{\"cor\":\"azul\",\"tipo\":\"off-road\"}").patioId(patio).build(),
        Moto.builder().placa("BCD0J10").modelo("Shineray Jet").ano(2023).status(StatusMoto.DISPONIVEL).rfidTag("RFID0010").dataAquisicao(LocalDate.of(2023, 1, 5)).ultimaManutencao(LocalDate.of(2024, 5, 9)).imagemReferencia("https://exemplo.com/motos/10.png").caracteristicasVisuais("{\"cor\":\"prata\",\"tipo\":\"cubs\"}").patioId(patio).build()
    );
    motoRepository.saveAll(motos);


    // Cria 10 detecções visuais
  var deteccoes = List.of(
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(120.5).coordenadaYImagem(85.2).coordenadaXPatio(10.4).coordenadaYPatio(22.1).imagemCaptura("/imagens/101_cam1.jpg").moto(motos.get(0)).camera(camera1).build(),
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(112.0).coordenadaYImagem(78.6).coordenadaXPatio(12.3).coordenadaYPatio(25.5).imagemCaptura("/imagens/101_cam2.jpg").moto(motos.get(1)).camera(camera2).build(),
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(105.7).coordenadaYImagem(90.0).coordenadaXPatio(14.1).coordenadaYPatio(27.3).imagemCaptura("/imagens/102_cam1.jpg").moto(motos.get(2)).camera(camera1).build(),
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(98.3).coordenadaYImagem(70.4).coordenadaXPatio(16.0).coordenadaYPatio(29.0).imagemCaptura("/imagens/103_cam3.jpg").moto(motos.get(3)).camera(camera2).build(),
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(102.1).coordenadaYImagem(88.2).coordenadaXPatio(18.5).coordenadaYPatio(21.1).imagemCaptura("/imagens/104_cam2.jpg").moto(motos.get(4)).camera(camera2).build(),
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(117.4).coordenadaYImagem(95.0).coordenadaXPatio(20.0).coordenadaYPatio(23.5).imagemCaptura("/imagens/105_cam1.jpg").moto(motos.get(5)).camera(camera1).build(),
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(109.6).coordenadaYImagem(82.8).coordenadaXPatio(22.7).coordenadaYPatio(20.3).imagemCaptura("/imagens/106_cam3.jpg").moto(motos.get(6)).camera(camera2).build(),
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(111.9).coordenadaYImagem(77.0).coordenadaXPatio(11.5).coordenadaYPatio(24.2).imagemCaptura("/imagens/107_cam2.jpg").moto(motos.get(7)).camera(camera2).build(),
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(113.5).coordenadaYImagem(86.3).coordenadaXPatio(13.3).coordenadaYPatio(26.0).imagemCaptura("/imagens/108_cam1.jpg").moto(motos.get(8)).camera(camera1).build(),
    DeteccaoVisual.builder().timesTamp(LocalDateTime.now().minusDays(new Random().nextInt(30))).coordenadaXImagem(107.8).coordenadaYImagem(79.6).coordenadaXPatio(15.0).coordenadaYPatio(28.8).imagemCaptura("/imagens/109_cam3.jpg").moto(motos.get(9)).camera(camera1).build()
);

deteccaoVisualRepository.saveAll(deteccoes);


    
    var zonas = List.of(
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
    var localizacoes = List.of(
    LocalizacaoMoto.builder().coordenadaX(10.5).coordenadaY(20.3).timestamp(LocalDateTime.now().minusHours(2)).status(StatusLocalizacao.EM_MOVIMENTO).fonteDados(FonteDados.VISAO_COMPUTACIONAL).confiabilidade(0.95).motoId(motos.get(0)).patio(patio).build(),
    LocalizacaoMoto.builder().coordenadaX(11.2).coordenadaY(21.0).timestamp(LocalDateTime.now().minusHours(3)).status(StatusLocalizacao.ESTACIONADA).fonteDados(FonteDados.RFID).confiabilidade(0.85).motoId(motos.get(1)).patio(patio).build(),
    LocalizacaoMoto.builder().coordenadaX(9.8).coordenadaY(19.7).timestamp(LocalDateTime.now().minusHours(1)).status(StatusLocalizacao.ESTACIONADA).fonteDados(FonteDados.FUSAO).confiabilidade(0.90).motoId(motos.get(2)).patio(patio).build(),
    LocalizacaoMoto.builder().coordenadaX(12.0).coordenadaY(22.4).timestamp(LocalDateTime.now().minusHours(4)).status(StatusLocalizacao.ESTACIONADA).fonteDados(FonteDados.MANUAL).confiabilidade(0.80).motoId(motos.get(3)).patio(patio).build(),
    LocalizacaoMoto.builder().coordenadaX(10.9).coordenadaY(20.8).timestamp(LocalDateTime.now().minusHours(5)).status(StatusLocalizacao.ESTACIONADA).fonteDados(FonteDados.VISAO_COMPUTACIONAL).confiabilidade(0.92).motoId(motos.get(4)).patio(patio).build(),
    LocalizacaoMoto.builder().coordenadaX(11.5).coordenadaY(21.3).timestamp(LocalDateTime.now().minusHours(6)).status(StatusLocalizacao.ESTACIONADA).fonteDados(FonteDados.RFID).confiabilidade(0.88).motoId(motos.get(5)).patio(patio).build(),
    LocalizacaoMoto.builder().coordenadaX(9.7).coordenadaY(19.9).timestamp(LocalDateTime.now().minusHours(7)).status(StatusLocalizacao.ESTACIONADA).fonteDados(FonteDados.FUSAO).confiabilidade(0.83).motoId(motos.get(6)).patio(patio).build(),
    LocalizacaoMoto.builder().coordenadaX(12.3).coordenadaY(22.1).timestamp(LocalDateTime.now().minusHours(8)).status(StatusLocalizacao.EM_MOVIMENTO).fonteDados(FonteDados.MANUAL).confiabilidade(0.79).motoId(motos.get(7)).patio(patio).build(),
    LocalizacaoMoto.builder().coordenadaX(10.8).coordenadaY(20.6).timestamp(LocalDateTime.now().minusHours(9)).status(StatusLocalizacao.ESTACIONADA).fonteDados(FonteDados.VISAO_COMPUTACIONAL).confiabilidade(0.91).motoId(motos.get(8)).patio(patio).build(),
    LocalizacaoMoto.builder().coordenadaX(11.0).coordenadaY(21.5).timestamp(LocalDateTime.now().minusHours(10)).status(StatusLocalizacao.ESTACIONADA).fonteDados(FonteDados.RFID).confiabilidade(0.87).motoId(motos.get(9)).patio(patio).build()
);
localizacaoRepository.saveAll(localizacoes);

    var tipos = List.of(
        "Entrada",
        "Saída",
        "Manutenção",
        "Aluguel",
        "Retorno",
        "Verificação",
        "Atualização",
        "Falha detectada",
        "Reparo realizado",
        "Limpeza",
        "Inspeção",
        "Troca de peça",
        "Carga de bateria",
        "Teste de sistema",
        "Movimentação"
    );

    var observacoes = List.of(
        "Evento automático pelo sistema",
        "Usuário solicitou manutenção",
        "Verificação diária concluída",
        "Aluguel iniciado pelo cliente",
        "Retorno após manutenção",
        "Falha no motor detectada",
        "Peça trocada com sucesso",
        "Carga realizada com sucesso",
        "Limpeza efetuada no pátio",
        "Atualização do software",
        "Evento manual registrado",
        "Teste de sensores concluído",
        "Movimentação para outro pátio",
        "Inspeção sem problemas",
        "Falha de comunicação"
    );

    Random random = new Random();
    var eventos = new ArrayList<EventoMoto>();

    for (int i = 0; i < 15; i++) {
        eventos.add(EventoMoto.builder()
            .tipo(tipos.get(random.nextInt(tipos.size())))
            .timesTamp(LocalDateTime.now().minusDays(random.nextInt(30)).minusHours(random.nextInt(24)))
            .observacao(observacoes.get(random.nextInt(observacoes.size())))
            .fonteEvento(FonteEvento.values()[random.nextInt(FonteEvento.values().length)])
            .usuarioId(usuarios.get(random.nextInt(usuarios.size())))
            .motoId(motos.get(random.nextInt(motos.size())))
            .build()
        );
    }
    eventoMotoRepository.saveAll(eventos);

    
    }
}
