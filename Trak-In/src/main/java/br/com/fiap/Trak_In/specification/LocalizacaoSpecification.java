package br.com.fiap.Trak_In.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Trak_In.controller.LocalizacaoController.LocalizacaoFilter;
import br.com.fiap.Trak_In.model.LocalizacaoMoto;
import jakarta.persistence.criteria.Predicate;

public class LocalizacaoSpecification {
     public static Specification<LocalizacaoMoto> withFilters(LocalizacaoFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

           // filtrar por Status de localização - ESTACIONADA, EM_MOVIMENTO
            if (filter.status() != null) {
                predicates.add(cb.equal(root.get("status"), filter.status()));
            }

            // filtrar por fonte de dados - RFID, VISAO_COMPUTACIONAL, FUSAO, MANUAL
            if (filter.fonteDados() != null) {
                predicates.add(cb.equal(root.get("fonteDados"), filter.fonteDados()));
            }

            //filtrar por placa da moto
            if (filter.moto() != null) {
                predicates.add(cb.equal(root.get("moto").get("placa"), filter.moto()));
            }

            //filtrar por ID do pátio
            if (filter.patioId() != null) {
                predicates.add(cb.equal(root.get("patio").get("id"), filter.patioId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
