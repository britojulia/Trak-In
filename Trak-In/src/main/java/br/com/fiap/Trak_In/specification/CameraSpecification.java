package br.com.fiap.Trak_In.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Trak_In.controller.CameraController.CameraFilter;
import br.com.fiap.Trak_In.model.Camera;
import jakarta.persistence.criteria.Predicate;

public class CameraSpecification {
    public static Specification<Camera> withFilters(CameraFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // Filtrar por status da câmera (ATIVO, INATIVO, MANUTENCAO)
            if (filter.status() != null) {
                predicates.add(cb.equal(root.get("status"), filter.status()));
            }

             // Filtrar por pátio (relacionamento)
            if (filter.idPatio() != null) {
                predicates.add(cb.equal(root.get("patio").get("id"), filter.idPatio()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
