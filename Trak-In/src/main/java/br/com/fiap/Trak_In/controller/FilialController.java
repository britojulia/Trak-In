package br.com.fiap.Trak_In.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.fiap.Trak_In.model.Filial;
import br.com.fiap.Trak_In.model.Moto;
import br.com.fiap.Trak_In.repository.FilialRepository;
import br.com.fiap.Trak_In.repository.MotoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/filial")
public class FilialController {
    
    
    @Autowired
    private FilialRepository repository;

    //listar todas as filiais cadastradas
    @GetMapping
    @Cacheable("filiais")
    @Operation(description = "listar todas as filiais", tags = "filiais", summary = "Lista de filiais")
    public List<Filial> index(){
        log.info("Buscando as filiais cadastradas");
        return repository.findAll();
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
    public Filial get(@PathVariable Long id){
        log.info("buscando por filial" + id);
        return getFilial(id);
    }

    //atualizar 
    @PutMapping("{id}")
    public Filial update(@PathVariable Long id, @RequestBody @Valid Filial filial){
        log.info("Atualizando info da filial");
        getFilial(id);
        return repository.save(filial);
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
                "Moto não encontrada"
            ));
    }
}
