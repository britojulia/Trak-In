package br.com.fiap.Trak_In.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Trak_In.controller.ZonaPatioController.ZonaFilter;
import br.com.fiap.Trak_In.model.ZonaPatio;
import jakarta.persistence.criteria.Predicate;


public class ZonaPatioSpecification {
     public static Specification<ZonaPatio> withFilters(ZonaFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

           // Filtrar por nome 
            if (filter.nome() != null && !filter.nome().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }

            // Filtrar por cor 
            if (filter.cor() != null && !filter.cor().isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("cor")), filter.cor().toLowerCase()));
            }

            // Filtrar por id do Patio (relacionamento)
            if (filter.idPatio() != null) {
                predicates.add(cb.equal(root.get("patio").get("id"), filter.idPatio()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
