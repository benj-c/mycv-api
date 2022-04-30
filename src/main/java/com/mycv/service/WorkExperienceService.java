package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.exception.ResponseType;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.EmploymentTypeEntity;
import com.mycv.model.entity.WorkExperienceEntity;
import com.mycv.model.request.WorkExpRequest;
import com.mycv.model.request.WorkExpUpdateRequest;
import com.mycv.repository.CvRepository;
import com.mycv.repository.WorkExperienceRepository;
import com.mycv.util.ApiUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Getter
@Slf4j
public class WorkExperienceService {

    private CvRepository cvRepository;
    private WorkExperienceRepository workExperienceRepository;

    public WorkExperienceService(
            WorkExperienceRepository workExperienceRepository,
            CvRepository cvRepository
    ) {
        this.workExperienceRepository = workExperienceRepository;
        this.cvRepository = cvRepository;
    }

    public void create(WorkExpRequest request) {
        //check if user has a cv
        String user = ApiUtil.getAuthUserName();
        log.info("retrieving cv by user|user:{}", user);
        CvEntity foundCv = getCvRepository().findByUserName(user).<ApiException>orElseThrow(() -> {
            log.error("there's no cv for user");
            throw new ApiException(ResponseType.CV_NOT_FOUND, "there's no cv for user");
        });

        EmploymentTypeEntity employmentType = new EmploymentTypeEntity();
        employmentType.setId(request.getEmploymentTypeId());

        WorkExperienceEntity entity = new WorkExperienceEntity();
        entity.setCvByCvId(foundCv);
        entity.setCity(request.getCity());
        entity.setCountry(request.getCountry());
        entity.setEmployer(request.getEmployer());
        entity.setEmploymentType(employmentType);
        entity.setEndDate(request.getEndDate());
        entity.setStartDate(request.getStartDate());
        entity.setJobTitle(request.getJobTitle());
        entity.setCurrentJob(request.getEndDate() == null);

        getWorkExperienceRepository().save(entity);
    }

    public void update(WorkExpUpdateRequest request) {
        String user = ApiUtil.getAuthUserName();
        WorkExperienceEntity entity = getWorkExperienceRepository().findByWeIdAndUsername(request.getId(), user).<ApiException>orElseThrow(() -> {
            log.error("there's no work experience to update");
            throw new ApiException(ResponseType.WORK_EXPERIENCE_NOT_FOUND, "there's no work experience to update");
        });

        if (request.getJobTitle() != null && !Objects.equals(request.getJobTitle(), entity.getJobTitle())) {
            entity.setJobTitle(request.getJobTitle());
        }
        if (request.getCountry() != null && !Objects.equals(request.getCountry(), entity.getCountry())) {
            entity.setCountry(request.getCountry());
        }
        if (request.getEmployer() != null && !Objects.equals(request.getEmployer(), entity.getEmployer())) {
            entity.setEmployer(request.getEmployer());
        }
        if (request.getCity() != null && !Objects.equals(request.getCity(), entity.getCity())) {
            entity.setCity(request.getCity());
        }
        if (request.getIsCurrentJob() != null && !Objects.equals(request.getIsCurrentJob(), entity.getCurrentJob())) {
            entity.setCurrentJob(request.getIsCurrentJob());
        }
        if (request.getStartDate() != null && !Objects.equals(request.getStartDate(), entity.getStartDate())) {
            entity.setStartDate(request.getStartDate());
        }
        if (request.getEndDate() != null && !Objects.equals(request.getEndDate(), entity.getEndDate())) {
            entity.setEndDate(request.getEndDate());
        }
        if (request.getEmploymentTypeId() != 0 && !Objects.equals(request.getEmploymentTypeId(), entity.getEmploymentType().getId())) {
            EmploymentTypeEntity employmentType = new EmploymentTypeEntity();
            employmentType.setId(request.getEmploymentTypeId());
            entity.setEmploymentType(employmentType);
        }

        getWorkExperienceRepository().save(entity);
    }

    public void delete(int id) {
        //check if user has access to the cv
        String user = ApiUtil.getAuthUserName();
        log.info("getting saved edu entry|Id:{}, user:{}", id, user);
        WorkExperienceEntity entity = getWorkExperienceRepository()
                .findByWeIdAndUsername(id, user).<ApiException>orElseThrow(() -> {
                    throw new ApiException(ResponseType.EDU_ENTRY_NOT_FOUND, "could not find work experience id:" + id);
                });

        log.info("deleting found entity");
        getWorkExperienceRepository().delete(entity);
    }
}
