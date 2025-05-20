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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import br.com.fiap.Trak_In.DTOs.FilialDTO;
import br.com.fiap.Trak_In.mappings.FilialMapper;
import br.com.fiap.Trak_In.model.Filial;
import br.com.fiap.Trak_In.repository.FilialRepository;
import br.com.fiap.Trak_In.specification.FilialSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/filial")
public class FilialController {

    public record FilialFilter(
    String nome,
    Long responsavelId,
    Long patioId
) {}

    
    @Autowired
    private FilialRepository repository;

    //listar todas as filiais cadastradas
    @GetMapping
    public Page<FilialDTO> index(FilialFilter filter,
    @PageableDefault(size = 5, sort= "id", direction = Direction.DESC) Pageable pageable){
        var specification = FilialSpecification.withFilters(filter);
        return repository.findAll(specification, pageable)
        .map(FilialMapper::toDTO);
    }

    //cadastrar 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação")})
    public Filial create(@RequestBody @Valid Filial filial){
        log.info("cadastrando nova filial" + filial.getNome());
        return repository.save(filial);
    }

    //buscar por id
    @GetMapping("{id}")
    public FilialDTO get(@PathVariable Long id){
        log.info("buscando por filial" + id);
        return FilialMapper.toDTO(getFilial(id));
    }
    

    //atualizar 
    @PutMapping("{id}")
    public FilialDTO update(@PathVariable Long id, @RequestBody @Valid Filial filial){
        log.info("Atualizando info da filial" + id);
        getFilial(id);
        filial.setId(id);
        Filial atualizada = repository.save(filial);
        return FilialMapper.toDTO(atualizada);
    }

    //deletar
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando filial" + id);
        repository.delete(getFilial((id)));
    }

    private Filial getFilial(Long id){
        return repository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "filial não encontrada"
            ));
    }
}
