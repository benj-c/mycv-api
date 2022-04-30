package com.mycv.repository;

import com.mycv.model.CommonData;
import com.mycv.model.entity.EmploymentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmploymentTypeRepository extends JpaRepository<EmploymentTypeEntity, Integer> {

    @Query("select new com.mycv.model.CommonData(e.id, e.type) from EmploymentTypeEntity e")
    List<CommonData> findAllAsCommonData();
}
