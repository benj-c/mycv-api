package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.exception.ResponseType;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.EducationHistoryEntity;
import com.mycv.model.entity.EducationStudyFieldEntity;
import com.mycv.model.request.EduEntryRequest;
import com.mycv.model.request.EduEntryUpdateRequest;
import com.mycv.repository.CvRepository;
import com.mycv.repository.EducationDetailRepository;
import com.mycv.util.ApiUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Getter
@Slf4j
public class EducationDetailService {

    private EducationDetailRepository educationDetailRepository;
    private CvRepository cvRepository;

    public EducationDetailService(
            EducationDetailRepository educationDetailRepository,
            CvRepository cvRepository
    ) {
        this.educationDetailRepository = educationDetailRepository;
        this.cvRepository = cvRepository;
    }

    public void addNewEducationDetailEntry(EduEntryRequest eduEntryRequest) {
        //check if user has access to the cv id
        String user = ApiUtil.getAuthUserName();
        int cvId = eduEntryRequest.getCvId();
        log.info("checking if user has access to cv|userId:{}, cvId:{}", user, cvId);
        getCvRepository().findByUserNameAndCvId(user, cvId).<ApiException>orElseThrow(() -> {
            log.error("user does not have access  to cv:{}", cvId);
            throw new ApiException(ResponseType.NO_ACCESS_TO_RESOURCE, "user has no access to cv:" + cvId);
        });

        log.info("creating EducationStudyFieldEntity with id:{}", eduEntryRequest.getEduFieldId());
        EducationStudyFieldEntity educationStudyFieldEntity = new EducationStudyFieldEntity();
        educationStudyFieldEntity.setId(eduEntryRequest.getEduFieldId());

        log.info("creating CvEntity with id:{}", cvId);
        CvEntity cvEntity = new CvEntity();
        cvEntity.setId(eduEntryRequest.getCvId());

        log.info("creating EducationHistoryEntity");
        EducationHistoryEntity entity = new EducationHistoryEntity();
        entity.setAwardedDate(eduEntryRequest.getAwardedDate());
        entity.setEducationStudyField(educationStudyFieldEntity);
        entity.setCvByCvId(cvEntity);
        entity.setLocation(eduEntryRequest.getLocation());
        entity.setInstitutionName(eduEntryRequest.getInstitutionName());

        log.info("saving entity");
        getEducationDetailRepository().save(entity);
    }

    public void updateEducationDetailEntry(EduEntryUpdateRequest request) {
        //check if user has access to the cv
        String user = ApiUtil.getAuthUserName();
        log.info("getting saved edu entry|Id:{}, user:{}", request.getId(), user);
        EducationHistoryEntity entity = getEducationDetailRepository()
                .findByEduIdAndUsername(request.getId(), user).<ApiException>orElseThrow(() -> {
                    throw new ApiException(ResponseType.EDU_ENTRY_NOT_FOUND, "could not find edu qualification id:" + request.getId());
                });

        if (request.getAwardedDate() != null && !Objects.equals(request.getAwardedDate(), entity.getAwardedDate())) {
            entity.setAwardedDate(request.getAwardedDate());
        }

        if (request.getEduFieldId() != 0 && request.getEduFieldId() != entity.getEducationStudyField().getId()) {
            log.info("creating EducationStudyFieldEntity with id:{}", request.getEduFieldId());
            EducationStudyFieldEntity educationStudyFieldEntity = new EducationStudyFieldEntity();
            educationStudyFieldEntity.setId(request.getEduFieldId());

            entity.setEducationStudyField(educationStudyFieldEntity);
        }

        if (request.getLocation() != null && !Objects.equals(request.getLocation(), entity.getLocation())) {
            entity.setLocation(request.getLocation());
        }

        if (request.getInstitutionName() != null && !Objects.equals(request.getInstitutionName(), entity.getInstitutionName())) {
            entity.setInstitutionName(request.getInstitutionName());
        }

        log.info("updating entity");
        getEducationDetailRepository().save(entity);
    }

    public void deleteEducationDetailEntry(int id) {
        //check if user has access to the cv
        String user = ApiUtil.getAuthUserName();
        log.info("getting saved edu entry|Id:{}, user:{}", id, user);
        EducationHistoryEntity entity = getEducationDetailRepository()
                .findByEduIdAndUsername(id, user).<ApiException>orElseThrow(() -> {
                    throw new ApiException(ResponseType.EDU_ENTRY_NOT_FOUND, "could not find edu qualification id:" + id);
                });

        log.info("deleting found entity");
        getEducationDetailRepository().delete(entity);
    }
}
