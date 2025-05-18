package br.com.fiap.Trak_In.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.fiap.Trak_In.model.Moto;
import br.com.fiap.Trak_In.model.Patio;
import br.com.fiap.Trak_In.model.TypesEnum.StatusMoto;
import br.com.fiap.Trak_In.repository.MotoRepository;
import br.com.fiap.Trak_In.specification.MotoSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/moto")
public class MotoController {

    public record MotoFilter(
    String placa,
    String modelo,
    Integer ano,
    StatusMoto status,
    String rfidTag,
    LocalDate dataAquisicao,
    LocalDate ultimaManutencaoInicio,
    LocalDate ultimaManutencaoFim,
    Patio patioId
) {}
    
    @Autowired
    private MotoRepository repository;

    //listar todas as motos cadastradas
    @GetMapping
     public Page<Moto> index(MotoFilter filter, 
        @PageableDefault(size = 5, sort = "date", direction = Direction.DESC) Pageable pageable) {
    var specification = MotoSpecification.withFilters(filter);
    return repository.findAll(specification, pageable);
    }

    //cadastrar motos
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação")})
    public Moto create(@RequestBody @Valid Moto moto){
        log.info("cadastrando nova moto" + moto.getPlaca());
        return repository.save(moto);
    }

    //buscar por id
    @GetMapping("{id}")
    public Moto get(@PathVariable Long id){
        log.info("buscando por moto" + id);
        return getMoto(id);
    }

    //atualizar infos moto
    @PutMapping("{id}")
    public Moto update(@PathVariable Long id, @RequestBody @Valid Moto moto){
        log.info("Atualizando info da moto" + moto + " " + moto);
        getMoto(id);
        moto.setId(id);
        return repository.save(moto);
    }

    //deletar moto
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando moto" + id);
        repository.delete(getMoto((id)));
    }

    private Moto getMoto(Long id){
        return repository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Moto não encontrada"
            ));
    }

}
