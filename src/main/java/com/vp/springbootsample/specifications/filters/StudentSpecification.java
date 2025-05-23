package com.vp.springbootsample.specifications.filters;

import com.vp.springbootsample.entities.Student;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentSpecification {
    public static Specification<Student> filter(Map<String, String> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            List<Predicate> predicatesOr = new ArrayList<>();

            filters.forEach((key, value) -> {
                if (value != null) {
                    if (key.equalsIgnoreCase("searchKeywords")) {
                        predicatesOr.add(criteriaBuilder.like(root.get("name"), "%" + value + "%"));
                        predicatesOr.add(criteriaBuilder.like(root.get("email"), "%" + value + "%"));
                        try { predicatesOr.add(criteriaBuilder.equal(root.get("age"), Integer.parseInt(value))); } catch (Exception e) { /* */ }
                    }
                }
            });

            // Create an 'or' predicate if there are any 'or' conditions
            if (!predicatesOr.isEmpty()) {
                Predicate orPredicate = criteriaBuilder.or(predicatesOr.toArray(new Predicate[0]));
                predicates.add(orPredicate);
            }

            // Return the final predicate with 'and' conditions
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
