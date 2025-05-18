package br.com.fiap.Trak_In.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.fiap.Trak_In.controller.DeteccaoVisualController.DeteccaoVisualFilter;
import br.com.fiap.Trak_In.model.DeteccaoVisual;
import br.com.fiap.Trak_In.model.Patio;
import br.com.fiap.Trak_In.repository.PatioRepository;
import br.com.fiap.Trak_In.specification.DetecacaoVisualSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/patio")
public class PatioController {

    @Autowired
    private PatioRepository repository;

    // LISTAR TODOS OS PATIOS
    @GetMapping
    public Page<Patio> index(
     @PageableDefault(size = 5, sort= "date", direction = Direction.DESC) Pageable pageable){
        return repository.findAll(pageable);
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
    public Patio get(@PathVariable Long id) {
        log.info("Buscando pátio por id: " + id);
        return getPatio(id);
    }

    // ATUALIZAR
    @PutMapping("{id}")
    public Patio update(@PathVariable Long id, @RequestBody @Valid Patio patio) {
        log.info("Atualizando pátio: " + id + " " + patio);
        getPatio(id);
        patio.setId(id);
        return repository.save(patio);
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

