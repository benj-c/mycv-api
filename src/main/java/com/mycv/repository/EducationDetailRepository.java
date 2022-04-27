package com.mycv.repository;

import com.mycv.model.entity.EducationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationDetailRepository extends JpaRepository<EducationHistoryEntity, Integer> {
}
