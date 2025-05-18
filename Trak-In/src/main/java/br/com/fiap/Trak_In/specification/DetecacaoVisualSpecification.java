package br.com.fiap.Trak_In.specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Trak_In.controller.DeteccaoVisualController.DeteccaoVisualFilter;
import br.com.fiap.Trak_In.model.DeteccaoVisual;

public class DetecacaoVisualSpecification {
    
    public static Specification<DeteccaoVisual> withFilters(DeteccaoVisualFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            //filtrar por data d inicio e fim
            
            if (filter.dataInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("timesTamp"), filter.dataInicio().atStartOfDay()));
            }

            if (filter.dataFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("timesTamp"), filter.dataFim().atTime(23, 59, 59)));
            }

             // Filtrar por ID da moto
            if (filter.placaMoto() != null) {
                predicates.add(cb.equal(root.get("moto").get("id"), filter.placaMoto()));
            }

            // Filtrar por ID da câmera
            if (filter.posicaoCamera() != null) {
                predicates.add(cb.equal(root.get("camera").get("id"), filter.posicaoCamera()));
            }

            // filtrar por coodernada X da Imagem
            if (filter.coordenadaXMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("coordenadaXImagem"), filter.coordenadaXMin()));
            }
            if (filter.coordenadaXMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("coordenadaXImagem"), filter.coordenadaXMax()));
            }

            // filtrar por coodernada y da Imagem
            if (filter.coordenadaYMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("coordenadaYImagem"), filter.coordenadaYMin()));
            }
            if (filter.coordenadaYMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                        root.get("coordenadaYImagem"), filter.coordenadaYMax()));
            }
            
            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
