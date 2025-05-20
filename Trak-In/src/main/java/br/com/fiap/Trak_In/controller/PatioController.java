package br.com.fiap.Trak_In.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

import br.com.fiap.Trak_In.DTOs.MotoDTO;
import br.com.fiap.Trak_In.DTOs.PatioDTO;
import br.com.fiap.Trak_In.controller.MotoController.MotoFilter;
import br.com.fiap.Trak_In.mappings.MotoMapper;
import br.com.fiap.Trak_In.mappings.PatioMapper;
import br.com.fiap.Trak_In.model.Patio;
import br.com.fiap.Trak_In.repository.PatioRepository;
import br.com.fiap.Trak_In.specification.MotoSpecification;
import br.com.fiap.Trak_In.specification.PatioSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/patio")
public class PatioController {

    
public record PatioFilter(
    String nome,
    String estado,
    String pais
) {}

    @Autowired
    private PatioRepository repository;

    // LISTAR TODOS OS PATIOS
    @GetMapping
    public Page<PatioDTO> index(PatioFilter filter,
     @PageableDefault(size = 5, sort= "id", direction = Direction.DESC) Pageable pageable){
        var specification = PatioSpecification.withFilters(filter); 
        return repository.findAll(specification, pageable)
        .map(PatioMapper::toDTO);
    }

    // CADASTRAR
    @PostMapping
    @CacheEvict(value = "patios", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação do pátio")})
    public Patio create(@RequestBody @Valid Patio patio) {
        log.info("Cadastrando novo pátio: " + patio.getNome());
        return repository.save(patio);
    }

    // BUSCAR POR ID
    @GetMapping("{id}")
    public PatioDTO get(@PathVariable Long id) {
        log.info("Buscando pátio por id: " + id);
        return PatioMapper.toDTO(getPatio(id));
    }

    // ATUALIZAR
    @PutMapping("{id}")
    public PatioDTO update(@PathVariable Long id, @RequestBody @Valid Patio patio) {
        log.info("Atualizando pátio: " + id );
        getPatio(id);
        patio.setId(id);
        Patio atualizada = repository.save(patio);
        return PatioMapper.toDTO(atualizada);
    }

    // DELETAR
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando pátio: " + id);
        repository.delete(getPatio(id));
    }

    
    private Patio getPatio(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                     "Pátio não encontrado"));
    }
}

