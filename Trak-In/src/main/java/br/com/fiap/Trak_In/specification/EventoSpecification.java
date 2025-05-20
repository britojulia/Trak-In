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

           /// filtrar por Tipo de evento
            if (filter.tipo() != null && !filter.tipo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("tipo")), "%" + filter.tipo().toLowerCase() + "%"));
            }

            //filtrar por  Intervalo de datas
            if (filter.dataInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("timesTamp"), filter.dataInicio()));
            }

            if (filter.dataFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("timesTamp"), filter.dataFim()));
            }

            // filtrar por Usuário associado ao evento
            if (filter.usuarioId() != null) {
                predicates.add(cb.equal(root.get("usuarioId").get("id"), filter.usuarioId()));
            }

            //filtrar por ID da Moto
            if (filter.motoId() != null) {
                predicates.add(cb.equal(root.get("motoId").get("id"), filter.motoId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
