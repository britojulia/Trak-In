package br.com.fiap.Trak_In.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.Trak_In.model.Filial;

public interface FilialRepository extends JpaRepository<Filial, Long>,JpaSpecificationExecutor<Filial> {

    
    
}
