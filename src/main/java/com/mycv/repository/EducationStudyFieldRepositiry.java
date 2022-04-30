package com.mycv.repository;

import com.mycv.model.CommonData;
import com.mycv.model.entity.EducationStudyFieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationStudyFieldRepositiry extends JpaRepository<EducationStudyFieldEntity, Integer> {

    @Query("select new com.mycv.model.CommonData(e.id, e.title) from EducationStudyFieldEntity e")
    List<CommonData> findAllAsCommonData();
}
