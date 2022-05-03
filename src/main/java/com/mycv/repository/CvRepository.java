package com.mycv.repository;

import com.mycv.model.entity.CvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CvRepository extends JpaRepository<CvEntity, Integer>, JpaSpecificationExecutor<CvEntity> {

    @Query("from CvEntity c where c.userByUserId.userName = :username and c.id = :cvId")
    Optional<CvEntity> findByUserNameAndCvId(
            @Param("username") String username,
            @Param("cvId") int cvId
    );

    @Query("from CvEntity c where c.userByUserId.userName = :username")
    Optional<CvEntity> findByUserName(
            @Param("username") String username
    );

}
