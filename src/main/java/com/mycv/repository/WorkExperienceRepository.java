package com.mycv.repository;

import com.mycv.model.entity.ProfessionalQualificationEntity;
import com.mycv.model.entity.SpecificSkilEntity;
import com.mycv.model.entity.WorkExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkExperienceRepository extends JpaRepository<WorkExperienceEntity, Integer> {

    @Query("select we from WorkExperienceEntity we, CvEntity c, UserEntity u where we.id = :weId and we.cvByCvId.id = c.id and c.userByUserId.id = u.id and u.userName = :user")
    Optional<WorkExperienceEntity> findByWeIdAndUsername(
            @Param("weId") int weId,
            @Param("user") String user
    );

    @Query("select e from WorkExperienceEntity e, CvEntity c, UserEntity u where e.cvByCvId = c.id and c.userByUserId = u.id and u.userName = :user")
    List<WorkExperienceEntity> findByUser(@Param("user") String user);
}
