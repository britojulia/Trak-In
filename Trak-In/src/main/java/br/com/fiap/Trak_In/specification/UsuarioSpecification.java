package br.com.fiap.Trak_In.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Trak_In.controller.UsuarioController.UsuarioFilter;
import br.com.fiap.Trak_In.model.Usuario;
import jakarta.persistence.criteria.Predicate;

public class UsuarioSpecification {
    public static Specification<Usuario> withFilters(UsuarioFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // filtrar por nome
            if (filter.nome() != null && !filter.nome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }

            // filtrar por email
            if (filter.email() != null && !filter.email().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + filter.email().toLowerCase() + "%"));
            }

            // filtar por perfil - ADMIN, OPERADOR, GERENTE
            if (filter.perfil() != null) {
                predicates.add(cb.equal(root.get("perfil"), filter.perfil()));
            }

            //filtrar pelo id da filial
            if (filter.filialId() != null) {
                predicates.add(cb.equal(root.get("filialId").get("id"), filter.filialId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
