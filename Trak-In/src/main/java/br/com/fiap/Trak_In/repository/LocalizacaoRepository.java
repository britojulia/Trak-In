package br.com.fiap.Trak_In.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Trak_In.model.LocalizacaoMoto;

public interface LocalizacaoRepository extends JpaRepository<LocalizacaoMoto, Long> {

    
}
