package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.exception.ResponseType;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.DegreeLevelEntity;
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

    public void create(EduEntryRequest eduEntryRequest) {
        //check if user has access to the cv id
        String user = ApiUtil.getAuthUserName();
        log.info("retrieving cv by user|user:{}", user);
        CvEntity foundCv = getCvRepository().findByUserName(user).<ApiException>orElseThrow(() -> {
            log.error("there's no cv for user");
            throw new ApiException(ResponseType.CV_NOT_FOUND, "there's no cv for user");
        });

        log.info("creating EducationStudyFieldEntity with id:{}", eduEntryRequest.getEduFieldId());
        EducationStudyFieldEntity educationStudyFieldEntity = new EducationStudyFieldEntity();
        educationStudyFieldEntity.setId(eduEntryRequest.getEduFieldId());

        log.info("creating CvEntity with id:{}", foundCv.getId());
        CvEntity cvEntity = new CvEntity();
        cvEntity.setId(foundCv.getId());

        log.info("creating DegreeLevelEntity with id:{}", eduEntryRequest.getDegreeLevelId());
        DegreeLevelEntity degreeLevelEntity = new DegreeLevelEntity();
        degreeLevelEntity.setId(eduEntryRequest.getDegreeLevelId());

        log.info("creating EducationHistoryEntity");
        EducationHistoryEntity entity = new EducationHistoryEntity();
        entity.setTitle(eduEntryRequest.getTitle());
        entity.setStartDate(eduEntryRequest.getStartDate());
        entity.setEndDate(eduEntryRequest.getEndDate());
        entity.setEducationStudyField(educationStudyFieldEntity);
        entity.setCvByCvId(cvEntity);
        entity.setLocation(eduEntryRequest.getLocation());
        entity.setInstitutionName(eduEntryRequest.getInstitutionName());
        entity.setDegreeLevel(degreeLevelEntity);

        log.info("saving entity");
        getEducationDetailRepository().save(entity);
    }

    public void update(EduEntryUpdateRequest request) {
        //check if user has access to the cv
        String user = ApiUtil.getAuthUserName();
        log.info("getting saved edu entry|Id:{}, user:{}", request.getId(), user);
        EducationHistoryEntity entity = getEducationDetailRepository()
                .findByEduIdAndUsername(request.getId(), user).<ApiException>orElseThrow(() -> {
                    throw new ApiException(ResponseType.EDU_ENTRY_NOT_FOUND, "could not find edu qualification id:" + request.getId());
                });

        if (request.getEndDate() != null && !Objects.equals(request.getEndDate(), entity.getEndDate())) {
            entity.setEndDate(request.getEndDate());
        }

        if (request.getStartDate() != null && !Objects.equals(request.getStartDate(), entity.getStartDate())) {
            entity.setStartDate(request.getStartDate());
        }

        if (request.getEduFieldId() != 0 && request.getEduFieldId() != entity.getEducationStudyField().getId()) {
            log.info("creating EducationStudyFieldEntity with id:{}", request.getEduFieldId());
            EducationStudyFieldEntity educationStudyFieldEntity = new EducationStudyFieldEntity();
            educationStudyFieldEntity.setId(request.getEduFieldId());

            entity.setEducationStudyField(educationStudyFieldEntity);
        }

        if (request.getTitle() != null && !Objects.equals(request.getTitle(), entity.getTitle())) {
            entity.setTitle(request.getTitle());
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

    public void delete(int id) {
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
