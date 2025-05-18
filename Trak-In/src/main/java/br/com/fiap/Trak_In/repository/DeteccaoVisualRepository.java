package br.com.fiap.Trak_In.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.Trak_In.model.DeteccaoVisual;

public interface DeteccaoVisualRepository extends JpaRepository<DeteccaoVisual, Long>, JpaSpecificationExecutor<DeteccaoVisual> {

    
}
