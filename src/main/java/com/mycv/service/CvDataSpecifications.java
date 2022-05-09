package com.mycv.service;

import com.mycv.model.entity.CvEntity;
import org.springframework.data.jpa.domain.Specification;

public class CvDataSpecifications {

    public static Specification<CvEntity> withFilterable() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDraft"), false);
    }

    public static Specification<CvEntity> withJobField(String f) {
        if (f == null) {
            return null;
        } else {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("cvJobFieldByJobFieldId").get("field"), f);
        }
    }

    public static Specification<CvEntity> withEduQualification(String f) {
        if (f == null) {
            return null;
        } else {
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("educationHistoriesById").get("title"), f);
        }
    }

    public static Specification<CvEntity> withDegreeLevel(int f) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.join("educationHistoriesById").join("degreeLevelByDegreeLevelId").get("id"), f
        );
    }

    public static Specification<CvEntity> withProfessionalQualification(String f) {
        if (f == null) {
            return null;
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.join("professionalQualificationsById").get("title"), f);
        }
    }

    public static Specification<CvEntity> withSkill(String f) {
        if (f == null) {
            return null;
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.join("specificSkilsById").get("title"), f);
        }
    }

    public static Specification<CvEntity> withWorkExperience(String f) {
        if (f == null) {
            return null;
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.join("workExperiencesById").get("jobTitle"), f);
        }
    }
}
