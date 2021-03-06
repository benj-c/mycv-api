package com.mycv.repository;

import com.mycv.model.entity.EducationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EducationDetailRepository extends JpaRepository<EducationHistoryEntity, Integer> {

    @Query("select edu from EducationHistoryEntity edu, CvEntity c, UserEntity u where edu.id = :eduId and edu.cvByCvId.id = c.id and c.userByUserId.id = u.id and u.userName = :user")
    Optional<EducationHistoryEntity> findByEduIdAndUsername(
            @Param("eduId") int eduId,
            @Param("user") String user
    );

    @Query("select e from EducationHistoryEntity e, CvEntity c, UserEntity u where e.cvByCvId = c.id and c.userByUserId = u.id and u.userName = :user")
    List<EducationHistoryEntity> findByUser(@Param("user") String user);
}
