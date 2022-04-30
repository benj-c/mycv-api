package com.mycv.repository;

import com.mycv.model.CommonData;
import com.mycv.model.entity.DegreeLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DegreeLevelRepository extends JpaRepository<DegreeLevelEntity, Integer> {

    @Query("select new com.mycv.model.CommonData(e.id, e.level) from DegreeLevelEntity e")
    List<CommonData> findAllAsCommonData();
}
