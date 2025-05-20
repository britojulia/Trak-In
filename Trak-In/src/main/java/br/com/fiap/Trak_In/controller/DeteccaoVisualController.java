package br.com.fiap.Trak_In.controller;

import java.time.LocalDate;

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

import br.com.fiap.Trak_In.DTOs.DeteccaoVisualDTO;
import br.com.fiap.Trak_In.mappings.DeteccaoVisualMapper;
import br.com.fiap.Trak_In.model.DeteccaoVisual;
import br.com.fiap.Trak_In.repository.DeteccaoVisualRepository;
import br.com.fiap.Trak_In.specification.DetecacaoVisualSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/deteccao")
public class DeteccaoVisualController {
    public record DeteccaoVisualFilter(String placaMoto,
    String posicaoCamera,
    LocalDate dataInicio,
    LocalDate dataFim){}

    @Autowired
    private DeteccaoVisualRepository repository;

    //listar todos os dados de detecção visual cadastrada
     @GetMapping
    public Page<DeteccaoVisualDTO> index(DeteccaoVisualFilter filter,
     @PageableDefault(size = 5, sort= "id", direction = Direction.DESC) Pageable pageable){
        var specification = DetecacaoVisualSpecification.withFilters(filter);
        return repository.findAll(specification, pageable)
        .map(DeteccaoVisualMapper::toDTO);
    }

    //cadastrar 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação")})
    public DeteccaoVisual create(@RequestBody @Valid DeteccaoVisual deteccaoVisual){
        log.info("cadastrando nova deteccao");
        return repository.save(deteccaoVisual);
    }

    //buscar por deteccao específica
    @GetMapping("{id}")
    public DeteccaoVisualDTO get(@PathVariable Long id){
        log.info("buscando por deteccao" + id);
        return DeteccaoVisualMapper.toDTO(getDeteccao(id));
    }

    //deletar ?
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando detecao" + id);
        repository.delete(getDeteccao((id)));
    }

    private DeteccaoVisual getDeteccao(Long id){
        return repository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Deteccao visual não encontrada"
            ));
    }
}
