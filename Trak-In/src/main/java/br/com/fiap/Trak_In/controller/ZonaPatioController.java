package br.com.fiap.Trak_In.controller;

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

import br.com.fiap.Trak_In.DTOs.ZonaPatioDTO;
import br.com.fiap.Trak_In.controller.DeteccaoVisualController.DeteccaoVisualFilter;
import br.com.fiap.Trak_In.mappings.ZonaPatioMapper;
import br.com.fiap.Trak_In.model.DeteccaoVisual;
import br.com.fiap.Trak_In.model.Usuario;
import br.com.fiap.Trak_In.model.ZonaPatio;
import br.com.fiap.Trak_In.repository.UsuarioRepository;
import br.com.fiap.Trak_In.repository.ZonaPatioRepository;
import br.com.fiap.Trak_In.specification.DetecacaoVisualSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/zona")
public class ZonaPatioController {
    @Autowired
    private ZonaPatioRepository repository;

    //listar todas as zonas cadastradas
    @GetMapping
    public Page<ZonaPatioDTO> index(
     @PageableDefault(size = 5, sort= "id", direction = Direction.DESC) Pageable pageable){
        return repository.findAll(pageable)
        .map(ZonaPatioMapper::toDTO);
    }

    //cadastrar 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação")})
    public ZonaPatio create(@RequestBody @Valid ZonaPatio zona){
        log.info("cadastrando nova zona de patio");
        return repository.save(zona);
    }

    //buscar por id
    @GetMapping("{id}")
    public ZonaPatioDTO get(@PathVariable Long id){
        log.info("buscando por zona de patio" + id);
        return ZonaPatioMapper.toDTO(getZona(id));
    }

    //atualizar infos moto
    @PutMapping("{id}")
    public ZonaPatio update(@PathVariable Long id, @RequestBody @Valid ZonaPatio zona){
        log.info("Atualizando info da zona patio");
        getZona(id);
        return repository.save(zona);
    }

    //deletar 
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando zona de patio" + id);
        repository.delete(getZona((id)));
    }

    private ZonaPatio getZona(Long id){
        return repository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Zona de patio não encontrada"
            ));
    }
}
