package br.com.fiap.Trak_In.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Trak_In.controller.EventoMotoController.EventoFilter;
import br.com.fiap.Trak_In.model.EventoMoto;
import jakarta.persistence.criteria.Predicate;

public class EventoSpecification {
        public static Specification<EventoMoto> withFilters(EventoFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

           /// filtrar por Tipo de evento - Limpeza, Aluguel, Teste de sistema
            if (filter.tipo() != null && !filter.tipo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("tipo")), "%" + filter.tipo().toLowerCase() + "%"));
            }

            //filtrar por  Intervalo de datas
            if (filter.dataInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("timesTamp"), filter.dataInicio().atStartOfDay()));
            }

            if (filter.dataFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("timesTamp"), filter.dataFim().atTime(23, 59, 59)));
            }

            // filtrar por Usuário associado ao evento
            if (filter.usuarioId() != null) {
                predicates.add(cb.equal(root.get("usuario").get("id"), filter.usuarioId()));
            }

            //filtrar por placa da Moto
            if (filter.placaMoto() != null) {
                predicates.add(cb.equal(root.get("moto").get("placa"), filter.placaMoto()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
