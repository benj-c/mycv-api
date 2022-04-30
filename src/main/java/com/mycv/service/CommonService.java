package com.mycv.service;

import com.mycv.model.CommonData;
import com.mycv.model.entity.DegreeLevelEntity;
import com.mycv.model.entity.EducationStudyFieldEntity;
import com.mycv.model.entity.EmploymentTypeEntity;
import com.mycv.repository.DegreeLevelRepository;
import com.mycv.repository.EducationStudyFieldRepositiry;
import com.mycv.repository.EmploymentTypeRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class CommonService {

    private DegreeLevelRepository degreeLevelRepository;
    private EducationStudyFieldRepositiry educationStudyFieldRepositiry;
    private EmploymentTypeRepository employmentTypeRepository;

    public CommonService(
            DegreeLevelRepository degreeLevelRepository,
            EducationStudyFieldRepositiry educationStudyFieldRepositiry,
            EmploymentTypeRepository employmentTypeRepository
    ) {
        this.degreeLevelRepository = degreeLevelRepository;
        this.educationStudyFieldRepositiry = educationStudyFieldRepositiry;
        this.employmentTypeRepository = employmentTypeRepository;
    }

    public List<CommonData> getDegreeLevels() {
        return getDegreeLevelRepository().findAllAsCommonData();
    }

    public List<CommonData> getEducationStudyFields() {
        return getEducationStudyFieldRepositiry().findAllAsCommonData();
    }

    public List<CommonData> getEmploymentTypes() {
        return getEmploymentTypeRepository().findAllAsCommonData();
    }
}
