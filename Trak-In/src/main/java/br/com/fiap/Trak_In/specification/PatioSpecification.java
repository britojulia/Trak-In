package br.com.fiap.Trak_In.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Trak_In.controller.PatioController.PatioFilter;
import br.com.fiap.Trak_In.model.Patio;
import jakarta.persistence.criteria.Predicate;

public class PatioSpecification {
    public static Specification<Patio> withFilters(PatioFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();       
            
            // filtro por nome do pátio 
            if (filter.nome() != null && !filter.nome().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }

            // filtro por Estado
            if (filter.estado() != null && !filter.estado().isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("estado")), filter.estado().toLowerCase()));
            }

            // filtro por País (Brasil ou México)
            if (filter.pais() != null && !filter.pais().isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("pais")), filter.pais().toLowerCase()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
