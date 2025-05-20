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

import br.com.fiap.Trak_In.DTOs.UsuarioDTO;
import br.com.fiap.Trak_In.mappings.UsuarioMapper;
import br.com.fiap.Trak_In.model.TypesEnum.PerfilUsuario;
import br.com.fiap.Trak_In.model.Usuario;
import br.com.fiap.Trak_In.repository.UsuarioRepository;
import br.com.fiap.Trak_In.specification.UsuarioSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class UsuarioController {
    public record UsuarioFilter(
    String nome,
    String email,
    PerfilUsuario perfil,
    Long filialId
) {}
    @Autowired
    private UsuarioRepository repository;

    //listar todas os usuarios cadastradas
    @GetMapping
    public Page<UsuarioDTO> index(UsuarioFilter filter, 
    @PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
    var specification = UsuarioSpecification.withFilters(filter);
    return repository.findAll(specification, pageable)
    .map(UsuarioMapper::toDTO);
    }

    //cadastrar motos
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {@ApiResponse(responseCode = "400", description = "Falha na validação")})
    public Usuario create(@RequestBody @Valid Usuario user){
        log.info("cadastrando novo usuario" + user.getNome());
        return repository.save(user);
    }

    //buscar por id
    @GetMapping("{id}")
    public UsuarioDTO get(@PathVariable Long id){
        log.info("buscando por usuario" + id);
        return UsuarioMapper.toDTO(getUser(id));
    }

    //atualizar infos moto
    @PutMapping("{id}")
    public UsuarioDTO update(@PathVariable Long id, @RequestBody @Valid Usuario user){
        log.info("Atualizando info do usuario" + user + " " + id);
        getUser(id);
        user.setId(id);
        Usuario atualizada = repository.save(user);
        return UsuarioMapper.toDTO(atualizada);
    }

    //deletar usuario
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando usuario" + id);
        repository.delete(getUser((id)));
    }

    private Usuario getUser(Long id){
        return repository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "usuario não encontrada"
            ));
    }
}
