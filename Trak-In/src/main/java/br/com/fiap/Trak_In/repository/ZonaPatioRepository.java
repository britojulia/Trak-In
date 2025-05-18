package br.com.fiap.Trak_In.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.Trak_In.model.ZonaPatio;

public interface ZonaPatioRepository extends JpaRepository<ZonaPatio, Long>,JpaSpecificationExecutor<ZonaPatio>  {

    
}
