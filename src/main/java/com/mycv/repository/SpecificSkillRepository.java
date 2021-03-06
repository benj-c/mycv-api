package com.mycv.repository;

import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.ProfessionalQualificationEntity;
import com.mycv.model.entity.SpecificSkilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpecificSkillRepository extends JpaRepository<SpecificSkilEntity, Integer> {

    @Query("select ss from SpecificSkilEntity ss, CvEntity c, UserEntity u where ss.id = :ssId and ss.cvByCvId.id = c.id and c.userByUserId.id = u.id and u.userName = :user")
    Optional<SpecificSkilEntity> findBySsIdAndUsername(
            @Param("ssId") int ssId,
            @Param("user") String user
    );

    @Query("select e from SpecificSkilEntity e, CvEntity c, UserEntity u where e.cvByCvId = c.id and c.userByUserId = u.id and u.userName = :user")
    List<SpecificSkilEntity> findByUser(@Param("user") String user);
}
