package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.exception.ResponseType;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.CvJobFieldEntity;
import com.mycv.model.entity.UserEntity;
import com.mycv.model.request.NewCv;
import com.mycv.repository.CvRepository;
import com.mycv.repository.UserRepository;
import com.mycv.util.ApiUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Getter
@Slf4j
public class CvService {

    private CvRepository cvRepository;
    private UserRepository userRepository;

    public CvService(CvRepository cvRepository, UserRepository userRepository) {
        this.cvRepository = cvRepository;
        this.userRepository = userRepository;
    }

    /**
     *
     * @param newCv
     */
    public void createNewCv(NewCv newCv) {
        String authUser = ApiUtil.getAuthUserName();
        log.info("getting user by authenticated username");
        UserEntity userEntity = getUserRepository().findByUserName(authUser).orElseThrow(() ->
                new ApiException(ResponseType.USER_NOT_FOUND, "user not found for name: " + authUser)
        );
        log.info("creating CvJobFieldEntity with id:{}", newCv.getJob_field_id());
        CvJobFieldEntity cvJobFieldEntity = new CvJobFieldEntity();
        cvJobFieldEntity.setId(newCv.getJob_field_id());

        log.info("creating CvEntity");
        CvEntity cvEntity = new CvEntity();
        cvEntity.setCity(newCv.getCity());
        cvEntity.setContactNumber(newCv.getContact_number());
        cvEntity.setCountry(newCv.getCountry());
        cvEntity.setCreatedDate(LocalDateTime.now());
        cvEntity.setEmail(newCv.getEmail());
        cvEntity.setFirstName(newCv.getFirst_name());
        cvEntity.setIsDraft(true);
        cvEntity.setSummery(newCv.getSummery());
        cvEntity.setSurname(newCv.getSurname());
        cvEntity.setUserByUserId(userEntity);
        cvEntity.setCvJobFieldByJobFieldId(cvJobFieldEntity);

        log.info("saving CvEntity");
        getCvRepository().save(cvEntity);
    }

    public CvEntity findCv(int cvId) {
        String authUser = ApiUtil.getAuthUserName();
        log.info("getting CV by username:{}, cvId:{}", authUser, cvId);
        CvEntity cvEntity = getCvRepository().findByUserNameAndCvId(authUser, cvId).orElseThrow(() ->
                new ApiException(ResponseType.CV_NOT_FOUND, "CV not found for id: " + cvId)
        );
        return cvEntity;
    }
}
