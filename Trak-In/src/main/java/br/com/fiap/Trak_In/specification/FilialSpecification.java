package br.com.fiap.Trak_In.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Trak_In.controller.FilialController.FilialFilter;
import br.com.fiap.Trak_In.model.Filial;
import jakarta.persistence.criteria.Predicate;

public class FilialSpecification {
     public static Specification<Filial> withFilters(FilialFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            //filtrar por nome 
            if (filter.nome() != null && !filter.nome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }
            
            //filtrar por id doresponsável
            if (filter.responsavelId() != null) {
                predicates.add(cb.equal(root.get("responsavelId").get("id"), filter.responsavelId()));
            }

            //filtra por id do patio
            if (filter.patioId() != null) {
                predicates.add(cb.equal(root.get("patio").get("id"), filter.patioId()));
            }
        
            
            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
