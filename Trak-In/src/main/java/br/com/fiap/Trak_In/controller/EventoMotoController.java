package br.com.fiap.Trak_In.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

import br.com.fiap.Trak_In.DTOs.EventoMotoDTO;
import br.com.fiap.Trak_In.mappings.EventoMotoMapper;
import br.com.fiap.Trak_In.model.EventoMoto;
import br.com.fiap.Trak_In.model.TypesEnum.FonteEvento;
import br.com.fiap.Trak_In.repository.EventoMotoRepository;
import br.com.fiap.Trak_In.specification.EventoSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/evento")
public class EventoMotoController {
    public record EventoFilter(
    String tipo,
    LocalDate dataInicio,
    LocalDate dataFim,
    FonteEvento fonteEvento,
    Long usuarioId,
    String placaMoto
) {}

    @Autowired
    private EventoMotoRepository repository;

    //listar todas os eventos da mota cadastrados
    @GetMapping
    public Page<EventoMotoDTO> index(EventoFilter filter,
     @PageableDefault(size = 5, sort= "id", direction = Direction.DESC) Pageable pageable){
    var specification = EventoSpecification.withFilters(filter);
    return repository.findAll(specification, pageable)
    .map(EventoMotoMapper::toDTO);
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
    public EventoMotoDTO get(@PathVariable Long id){
        log.info("buscando por evento" + id);
        return EventoMotoMapper.toDTO(getEvento(id));
    }

    //atualizar infos 
    @PutMapping("{id}")
    public EventoMotoDTO update(@PathVariable Long id, @RequestBody @Valid EventoMoto eventoMoto){
        log.info("Atualizando info de evento" + eventoMoto);
        getEvento(id);
        eventoMoto.setId(id);
        EventoMoto atualizada = repository.save(eventoMoto);
        return EventoMotoMapper.toDTO(atualizada);
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
