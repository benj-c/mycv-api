package com.mycv.repository;

import com.mycv.model.AuthData;
import com.mycv.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying
    @Query("update UserEntity u set u.isActive = :isEnabled where u.id = :uid")
    void actDeactUser(
            @Param("isEnabled") boolean isEnabled,
            @Param("uid") int uid
    );

    Optional<UserEntity> findByUserName(String userName);

    @Query("from UserEntity u where u.userName = :userName")
    Optional<UserEntity> findUserByUserName(@Param("userName") String userName);

    Optional<UserEntity> findById(int id);
}
