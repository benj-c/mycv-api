package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.exception.ResponseType;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.SpecificSkilEntity;
import com.mycv.model.request.SpecificSkilRequest;
import com.mycv.model.request.SpecificSkilUpdateRequest;
import com.mycv.repository.CvRepository;
import com.mycv.repository.SpecificSkillRepository;
import com.mycv.util.ApiUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@Getter
public class SpecificSkillService {

    private CvRepository cvRepository;
    private SpecificSkillRepository specificSkillRepository;

    public SpecificSkillService(
            SpecificSkillRepository specificSkillRepository,
            CvRepository cvRepository
    ) {
        this.specificSkillRepository = specificSkillRepository;
        this.cvRepository = cvRepository;
    }

    public List<SpecificSkilEntity> create(SpecificSkilRequest request) {
        //check if user has a cv
        String user = ApiUtil.getAuthUserName();
        log.info("retrieving cv by user|user:{}", user);
        CvEntity foundCv = getCvRepository().findByUserName(user).<ApiException>orElseThrow(() -> {
            log.error("there's no cv for user");
            throw new ApiException(ResponseType.CV_NOT_FOUND, "there's no cv for user");
        });

        CvEntity cvEntity = new CvEntity();
        cvEntity.setId(foundCv.getId());

        SpecificSkilEntity entity = new SpecificSkilEntity();
        entity.setDescription(request.getDescription());
        entity.setField(request.getField());
        entity.setTitle(request.getTitle());
        entity.setCvByCvId(cvEntity);

        getSpecificSkillRepository().save(entity);
        return getSpecificSkillRepository().findByUser(user);
    }

    public void update(SpecificSkilUpdateRequest request) {
        String user = ApiUtil.getAuthUserName();
        SpecificSkilEntity entity = getSpecificSkillRepository().findBySsIdAndUsername(request.getId(), user).<ApiException>orElseThrow(() -> {
            log.error("there's no specific skill to update");
            throw new ApiException(ResponseType.SPECIFIC_SKILL_NOT_FOUND, "there's no specific skill to update");
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

        getSpecificSkillRepository().save(entity);
    }

    public void delete(int id) {
        //check if user has access to the cv
        String user = ApiUtil.getAuthUserName();
        log.info("getting saved edu entry|Id:{}, user:{}", id, user);
        SpecificSkilEntity entity = getSpecificSkillRepository()
                .findBySsIdAndUsername(id, user).<ApiException>orElseThrow(() -> {
                    throw new ApiException(ResponseType.EDU_ENTRY_NOT_FOUND, "could not find specific skill id:" + id);
                });

        log.info("deleting found entity");
        getSpecificSkillRepository().delete(entity);
    }

    public List<SpecificSkilEntity> get(){
        String user = ApiUtil.getAuthUserName();
        return getSpecificSkillRepository().findByUser(user);
    }
}
