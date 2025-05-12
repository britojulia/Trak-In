package br.com.fiap.Trak_In.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Trak_In.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    
}
