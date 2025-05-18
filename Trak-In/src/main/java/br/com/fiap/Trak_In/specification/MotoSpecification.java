package br.com.fiap.Trak_In.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import br.com.fiap.Trak_In.controller.MotoController.MotoFilter;
import br.com.fiap.Trak_In.model.Moto;
import jakarta.persistence.criteria.Predicate;

public class MotoSpecification {
    
    public static Specification<Moto> withFilters(MotoFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            
            //filtra por Placa
            if (filter.placa() != null && !filter.placa().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("placa")), "%" + filter.placa().toLowerCase() + "%"));
            }

            //filtra por Modelo
            if (filter.modelo() != null && !filter.modelo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("modelo")), "%" + filter.modelo().toLowerCase() + "%"));
            }

            // filtra por rfid
            if (filter.rfidTag() != null && !filter.rfidTag().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("rfidTag")), "%" + filter.rfidTag().toLowerCase() + "%"));
            }
            //filtra por Status
            if (filter.status() != null) {
                predicates.add(cb.equal(root.get("status"), filter.status()));
            }

            // Data de aquisição (intervalo)
            if (filter.dataAquisicao() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataAquisicao"), filter.dataAquisicao()));
            }

            // Última manutenção (intervalo)
            if (filter.ultimaManutencaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("ultimaManutencao"), filter.ultimaManutencaoInicio()));
            }

            if (filter.ultimaManutencaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("ultimaManutencao"), filter.ultimaManutencaoFim()));
            }

            // Filtro por pátio
            if (filter.patioId() != null) {
                predicates.add(cb.equal(root.get("patioId").get("id"), filter.patioId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }

   
}
