package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.exception.ResponseType;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.EducationHistoryEntity;
import com.mycv.model.entity.EducationStudyFieldEntity;
import com.mycv.model.request.EduEntryRequest;
import com.mycv.repository.CvRepository;
import com.mycv.repository.EducationDetailRepository;
import com.mycv.util.ApiUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        entity.setEducationStudyFieldByEducationStudyFieldId(educationStudyFieldEntity);
        entity.setCvByCvId(cvEntity);
        entity.setLocation(eduEntryRequest.getLocation());
        entity.setInstituitionName(eduEntryRequest.getInstitutionName());

        log.info("saving entity");
        getEducationDetailRepository().save(entity);
    }
}
