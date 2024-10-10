package com.desafio.hotel.specification;

import com.desafio.hotel.exceptions.ValidateException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SpecificationValidator<T> {
    private Set<Specification> specifications = new HashSet();

    private SpecificationValidator() {
    }

    public static <T> SpecificationValidator<T> of() {
        return new SpecificationValidator();
    }

    public SpecificationValidator<T> add(Specification specification) {
        if (Objects.nonNull(specification)) {
            this.specifications.add(specification);
        }

        return this;
    }

    public void validateWithException(T candidate) throws ValidateException {
        this.buildResult(candidate).throwMessages();
    }

    private SpecificationResult<Specification> buildResult(T candidate) {
        SpecificationResult<Specification> specificationResult = new SpecificationResult();
        this.specifications.forEach((specification) -> {
            if (!specification.isSatisfiedBy(candidate)) {
                specificationResult.add(new ValidationMessage(specification.getMessage(), specification.getField()));
            }

        });
        return specificationResult;
    }
}
