package com.mycv.service;

import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.EducationHistoryEntity;
import com.mycv.model.entity.EducationStudyFieldEntity;
import com.mycv.model.request.EduEntryRequest;
import com.mycv.repository.EducationDetailRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class EducationDetailService {

    private EducationDetailRepository educationDetailRepository;

    public EducationDetailService(EducationDetailRepository educationDetailRepository) {
        this.educationDetailRepository = educationDetailRepository;
    }

    public void addNewEducationDetailEntry(EduEntryRequest eduEntryRequest) {
        log.info("creating EducationStudyFieldEntity with id:{}", eduEntryRequest.getEdu_field_id());
        EducationStudyFieldEntity educationStudyFieldEntity = new EducationStudyFieldEntity();
        educationStudyFieldEntity.setId(eduEntryRequest.getEdu_field_id());

        log.info("creating CvEntity with id:{}", eduEntryRequest.getCv_id());
        CvEntity cvEntity = new CvEntity();
        cvEntity.setId(eduEntryRequest.getCv_id());

        log.info("creating EducationHistoryEntity");
        EducationHistoryEntity entity = new EducationHistoryEntity();
        entity.setAwardedDate(eduEntryRequest.getAwarded_date());
        entity.setEducationStudyFieldByEducationStudyFieldId(educationStudyFieldEntity);
        entity.setCvByCvId(cvEntity);
        entity.setLocation(eduEntryRequest.getLocation());
        entity.setInstituitionName(eduEntryRequest.getInstitution_name());

        log.info("saving entity");
        getEducationDetailRepository().save(entity);
    }
}
