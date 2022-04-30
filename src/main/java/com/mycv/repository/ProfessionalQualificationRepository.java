package com.mycv.repository;

import com.mycv.model.entity.ProfessionalQualificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfessionalQualificationRepository extends JpaRepository<ProfessionalQualificationEntity, Integer> {

    @Query("select pq from ProfessionalQualificationEntity pq, CvEntity c, UserEntity u where pq.id = :pqId and pq.cvByCvId.id = c.id and c.userByUserId.id = u.id and u.userName = :user")
    Optional<ProfessionalQualificationEntity> findByPqIdAndUsername(
            @Param("pqId") int pqId,
            @Param("user") String user
    );

}
