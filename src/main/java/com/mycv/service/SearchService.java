package com.mycv.service;

import com.mycv.model.entity.CvEntity;
import com.mycv.repository.CvRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.mycv.service.CvDataSpecifications.*;

@Service
@Slf4j
@Getter
public class SearchService {

    @Autowired
    private CvRepository cvRepository;

    private static final String FILTER_JOB_SECTOR = "jf";
    private static final String FILTER_DEGREE_LEVEL = "el";
    private static final String FILTER_EDU_QUALIFICATION = "eq";
    private static final String FILTER_PROFESSIONAL_QUALIFICATION = "pq";
    private static final String FILTER_SKILL = "sk";
    private static final String FILTER_WORK_EXPERIENCE = "we";

    public List<CvEntity> search(String query) {
        String[] criterias = query.split(",");
        Specification<CvEntity> spec = Specification.where(withFilterable());
        for (int i = 0; i < criterias.length; i++) {
            String[] qParts = criterias[i].split(":");
            if (FILTER_JOB_SECTOR.equals(qParts[0])) {
                spec = spec.and(withJobField(qParts[1]));
            } else if (FILTER_DEGREE_LEVEL.equals(qParts[0])) {
                spec = spec.and(withDegreeLevel(Integer.valueOf(qParts[1])));
            } else if (FILTER_EDU_QUALIFICATION.equals(qParts[0])) {
                spec = spec.and(withEduQualification(qParts[1]));
            } else if (FILTER_PROFESSIONAL_QUALIFICATION.equals(qParts[0])) {
                spec = spec.and(withEduQualification(qParts[1]));
            } else if (FILTER_SKILL.equals(qParts[0])) {
                spec = spec.and(withEduQualification(qParts[1]));
            } else if (FILTER_WORK_EXPERIENCE.equals(qParts[0])) {
                spec = spec.and(withEduQualification(qParts[1]));
            }
        }
        return getCvRepository().findAll(spec);
    }
}
