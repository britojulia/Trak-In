package br.com.fiap.Trak_In.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import br.com.fiap.Trak_In.DTOs.LocalizacaoDTO;
import br.com.fiap.Trak_In.mappings.LocalizacaoMapper;
import br.com.fiap.Trak_In.model.LocalizacaoMoto;
import br.com.fiap.Trak_In.model.TypesEnum.FonteDados;
import br.com.fiap.Trak_In.model.TypesEnum.StatusLocalizacao;
import br.com.fiap.Trak_In.repository.LocalizacaoRepository;
import br.com.fiap.Trak_In.specification.LocalizacaoSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/localizacao")
public class LocalizacaoController {

    public record LocalizacaoFilter(
    StatusLocalizacao status,
    FonteDados fonteDados,
    String moto,
    Long patioId
) {}

    @Autowired
    private LocalizacaoRepository repository;

    //listar todas localizacoes de motos
    @GetMapping
    public Page<LocalizacaoDTO> index(LocalizacaoFilter filter, 
    @PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
    var specification = LocalizacaoSpecification.withFilters(filter);
    return repository.findAll(specification, pageable)
    .map(LocalizacaoMapper::toDTO);
    }

    //cadastrar 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação")})
    public LocalizacaoMoto create(@RequestBody @Valid LocalizacaoMoto localizacao){
        log.info("cadastrando nova localizacao" );
        return repository.save(localizacao);
    }

    //buscar por id
    @GetMapping("{id}")
    public LocalizacaoDTO get(@PathVariable Long id){
        log.info("buscando por localizacao" + id);
        return LocalizacaoMapper.toDTO(getLocal(id));
    }

    //deletar 
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando localizacao" + id);
        repository.delete(getLocal((id)));
    }

    private LocalizacaoMoto getLocal(Long id){
        return repository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "localizacao não encontrada"
            ));
    }
}
