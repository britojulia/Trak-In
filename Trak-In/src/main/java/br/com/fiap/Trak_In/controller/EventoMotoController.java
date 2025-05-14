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

import br.com.fiap.Trak_In.model.EventoMoto;
import br.com.fiap.Trak_In.model.Moto;
import br.com.fiap.Trak_In.repository.EventoMotoRepository;
import br.com.fiap.Trak_In.repository.MotoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/evento")
public class EventoMotoController {
    @Autowired
    private EventoMotoRepository repository;

    //listar todas os eventos da mota cadastrados
    @GetMapping
    @Cacheable("eventos")
    @Operation(description = "listar todas eventos de moto", tags = "eventos", summary = "Lista de eventos")
    public List<EventoMoto> index(){
        log.info("Buscando os eventos cadastradas");
        return repository.findAll();
    }

    //cadastrar novo evento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação")})
    public EventoMoto create(@RequestBody @Valid EventoMoto eventoMoto){
        log.info("cadastrando novo evento");
        return repository.save(eventoMoto);
    }

    //buscar por id
    @GetMapping("{id}")
    public EventoMoto get(@PathVariable Long id){
        log.info("buscando por moto" + id);
        return getEvento(id);
    }

    //atualizar infos 
    @PutMapping("{id}")
    public EventoMoto update(@PathVariable Long id, @RequestBody @Valid EventoMoto eventoMoto){
        log.info("Atualizando info" + eventoMoto);
        getEvento(id);
        return repository.save(eventoMoto);
    }

    //deletar
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando moto" + id);
        repository.delete(getEvento((id)));
    }

    private EventoMoto getEvento(Long id){
        return repository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Evento de moto não encontrada"
            ));
    }

}
