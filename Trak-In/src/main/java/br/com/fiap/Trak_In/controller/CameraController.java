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

import br.com.fiap.Trak_In.DTOs.CameraDTO;
import br.com.fiap.Trak_In.controller.DeteccaoVisualController.DeteccaoVisualFilter;
import br.com.fiap.Trak_In.mappings.CameraMapper;
import br.com.fiap.Trak_In.mappings.ZonaPatioMapper;
import br.com.fiap.Trak_In.model.Camera;
import br.com.fiap.Trak_In.model.DeteccaoVisual;
import br.com.fiap.Trak_In.model.Moto;
import br.com.fiap.Trak_In.model.ZonaPatio;
import br.com.fiap.Trak_In.repository.CameraRepository;
import br.com.fiap.Trak_In.repository.MotoRepository;
import br.com.fiap.Trak_In.specification.DetecacaoVisualSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/camera")
public class CameraController {
    @Autowired
    private CameraRepository repository;

    //listar todas as cameras cadastradas
    @GetMapping
    public Page<CameraDTO> index(
    @PageableDefault(size = 5, sort= "id", direction = Direction.DESC) Pageable pageable){
    return repository.findAll( pageable)
    .map(CameraMapper::toDTO);
            
    }

    //buscar por id
    @GetMapping("{id}")
    public CameraDTO get(@PathVariable Long id) {
        log.info("Buscando por camera " + id);
        return CameraMapper.toDTO(getCamera(id));
    }

    //cadastrar 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação")})
    public Camera create(@RequestBody @Valid Camera camera){
        log.info("cadastrando nova camera");
        return repository.save(camera);
    }

    //atualizar infos 
    @PutMapping("{id}")
    public CameraDTO update(@PathVariable Long id, @RequestBody @Valid Camera camera){
        log.info("Atualizando info da camera" + id);
        getCamera(id);
        camera.setId(id);
        Camera atualizada = repository.save(camera);
        return CameraMapper.toDTO(atualizada);
    }

    //deletar 
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando camera" + id);
        repository.delete(getCamera((id)));
    }

    private Camera getCamera(Long id){
        return repository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Camera não encontrada"
            ));
    }
}
