package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.exception.ResponseType;
import com.mycv.model.AuthData;
import com.mycv.model.UserRoles;
import com.mycv.model.entity.UserEntity;
import com.mycv.model.entity.UserRoleEntity;
import com.mycv.model.request.UserCredentialsRequest;
import com.mycv.repository.UserRepository;
import com.mycv.util.ApiUtil;
import com.mycv.util.JwtUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.lang.JoseException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Slf4j
@Data
public class UserService {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private JwtUtil jwtUtil;

    public UserService(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.jwtUtil = jwtUtil;
    }

    /**
     * generate access token using given details
     * @param username
     * @return
     * @throws JoseException
     */
    public AuthData createAccessToken(String username) throws JoseException {
        UserEntity userEntity = getUserRepository().findByUserName(username)
                .orElseThrow(() -> new ApiException(ResponseType.USER_NOT_FOUND, username + " is not found"));
        String token = getJwtUtil().generateToken(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getUserRoleByUserRoleId().getType()
        );
        return AuthData.builder()
                .createdDate(userEntity.getCreatedDate())
                .username(userEntity.getUserName())
                .isAvtive(userEntity.getActive())
                .scope(userEntity.getUserRoleByUserRoleId().getType())
                .email(userEntity.getEmail())
                .token(token)
                .build();
    }

    /**
     * returns user details for given user id,
     * if the userid does not belong to the user who authenticated then throws an exception
     * @param userId
     * @return
     */
    public UserEntity getUserById(int userId) {
        UserEntity user = getUserRepository().findById(userId).<ApiException>orElseThrow(() ->
            new ApiException(ResponseType.USER_NOT_FOUND, null)
        );

        if (Objects.equals(user.getUserName(), ApiUtil.getAuthUserName())) {
            return user;
        } else {
            throw new ApiException(ResponseType.USER_NOT_FOUND, null);
        }
    }

    /**
     * activates or deactivates a user.
     * admin can de-activate any user
     * job-seeker cannot actdeact anyone including them selves
     * agents can actdeact only job-seekers, not admins or same user
     * @param userId
     * @return
     */
    @Transactional
    public boolean actDeatUser(int userId) {
        String role = ApiUtil.getAuthentication().getAuthorities().stream().findFirst().get().getAuthority();
        UserEntity userEntity = getUserRepository().findById(userId)
                .orElseThrow(() -> new ApiException(ResponseType.USER_NOT_FOUND, "user not found for id: " + userId));
        boolean b = !userEntity.getActive();
        log.info("status value to be updated:{}, user role:{}", b, role);
        log.info("target user id:{}, role:{}", userEntity.getId(), userEntity.getUserRoleByUserRoleId().getType());
        //admin user can enable or disable any user
        if (Objects.equals(role, UserRoles.ADMIN)) {
            getUserRepository().actDeactUser(b, userId);
            return b;
        } else if (Objects.equals(role, UserRoles.AGENT)) {
            //agent user can only enable or disable job_seeker users
            if (Objects.equals(userEntity.getUserRoleByUserRoleId().getType(), UserRoles.JOB_SEEKER)) {
                //can act-deact job-seeker users
                getUserRepository().actDeactUser(b, userId);
                return b;
            } else {
                //cannot act-deact same agent or other agents or admins
                throw new ApiException(ResponseType.INVALID_OPERATION, "cannot update user: " + userId);
            }
        } else {
            throw new ApiException(ResponseType.INVALID_OPERATION, "invalid operation");
        }
    }

    @Transactional
    public void createUser(UserCredentialsRequest request) {
        //check if user exists
        getUserRepository().findByUserName(request.getUsername())
                .ifPresent(e -> {
                    throw new ApiException(ResponseType.USER_ALREADY_FOUND, null);
                });

        UserRoleEntity userRole = getUserRoleRepository().findByType(request.getRole())
                .orElseThrow(() -> new ApiException(ResponseType.USER_ROLE_NOT_FOUND, null));

        UserEntity userEntity = new UserEntity();
        userEntity.setActive(true);
        userEntity.setUserName(request.getUsername());
        userEntity.setCreatedDate(LocalDate.now());
        userEntity.setEmail(request.getUsername());
        userEntity.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        userEntity.setUserRoleByUserRoleId(userRole);

        getUserRepository().save(userEntity);
    }
}
