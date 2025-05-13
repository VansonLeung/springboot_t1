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

            filters.forEach((key, value) -> {
                if (value != null) {
                    if (key.equalsIgnoreCase("name")) {
                        predicates.add(criteriaBuilder.like(root.get("name"), "%" + value + "%"));
                    } else if (key.equalsIgnoreCase("email")) {
                        predicates.add(criteriaBuilder.like(root.get("email"), "%" + value + "%"));
                    } else if (key.equalsIgnoreCase("age")) {
                        predicates.add(criteriaBuilder.equal(root.get("age"), Integer.parseInt(value)));
                    }
                }
            });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
