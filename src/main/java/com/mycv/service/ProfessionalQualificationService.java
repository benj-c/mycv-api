package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.exception.ResponseType;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.ProfessionalQualificationEntity;
import com.mycv.model.request.ProQualRequest;
import com.mycv.model.request.ProfQualUpdateRequest;
import com.mycv.repository.CvRepository;
import com.mycv.repository.ProfessionalQualificationRepository;
import com.mycv.util.ApiUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Getter
@Slf4j
public class ProfessionalQualificationService {

    private CvRepository cvRepository;
    private ProfessionalQualificationRepository qualificationRepository;

    public ProfessionalQualificationService(
            ProfessionalQualificationRepository qualificationRepository,
            CvRepository cvRepository
    ) {
        this.qualificationRepository = qualificationRepository;
        this.cvRepository = cvRepository;
    }

    public List<ProfessionalQualificationEntity> create(ProQualRequest request) {
        //check if user has access to the cv id
        String user = ApiUtil.getAuthUserName();
        log.info("retrieving cv by user|user:{}", user);
        CvEntity foundCv = getCvRepository().findByUserName(user).<ApiException>orElseThrow(() -> {
            log.error("there's no cv for user");
            throw new ApiException(ResponseType.CV_NOT_FOUND, "there's no cv for user");
        });

        CvEntity cvEntity = new CvEntity();
        cvEntity.setId(foundCv.getId());

        ProfessionalQualificationEntity entity = new ProfessionalQualificationEntity();
        entity.setDescription(request.getDescription());
        entity.setField(request.getField());
        entity.setTitle(request.getTitle());
        entity.setCvByCvId(cvEntity);

        getQualificationRepository().save(entity);
        return getQualificationRepository().findByUser(user);
    }

    public void update(ProfQualUpdateRequest request) {
        String user = ApiUtil.getAuthUserName();
        ProfessionalQualificationEntity entity = getQualificationRepository().findByPqIdAndUsername(request.getId(), user).<ApiException>orElseThrow(() -> {
            log.error("there's no qualification to update");
            throw new ApiException(ResponseType.PROF_QUAL_NOT_FOUND, "there's no qualification to update");
        });

        if (request.getTitle() != null && !Objects.equals(request.getTitle(), entity.getTitle())) {
            entity.setTitle(request.getTitle());
        }
        if (request.getField() != null && !Objects.equals(request.getField(), entity.getField())) {
            entity.setField(request.getField());
        }
        if (request.getDescription() != null && !Objects.equals(request.getDescription(), entity.getDescription())) {
            entity.setDescription(request.getDescription());
        }

        getQualificationRepository().save(entity);
    }

    public void delete(int id) {
        //check if user has access to the cv
        String user = ApiUtil.getAuthUserName();
        log.info("getting saved edu entry|Id:{}, user:{}", id, user);
        ProfessionalQualificationEntity entity = getQualificationRepository()
                .findByPqIdAndUsername(id, user).<ApiException>orElseThrow(() -> {
                    throw new ApiException(ResponseType.EDU_ENTRY_NOT_FOUND, "could not find professional skill id:" + id);
                });

        log.info("deleting found entity");
        getQualificationRepository().delete(entity);
    }
}
