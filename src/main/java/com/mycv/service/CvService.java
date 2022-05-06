package com.mycv.service;

import com.mycv.exception.ApiException;
import com.mycv.exception.ResponseType;
import com.mycv.model.CvData;
import com.mycv.model.GeneratedDocxDocDetail;
import com.mycv.model.UserRoles;
import com.mycv.model.entity.CvEntity;
import com.mycv.model.entity.CvJobFieldEntity;
import com.mycv.model.entity.UserEntity;
import com.mycv.model.request.CvUpdateRequest;
import com.mycv.model.request.NewCv;
import com.mycv.repository.CvRepository;
import com.mycv.repository.UserRepository;
import com.mycv.util.ApiUtil;
import com.mycv.util.FileUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
     * @param newCv
     */
    @Transactional
    public CvEntity createNewCv(NewCv newCv) {
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
        cvEntity.setDraft(true);
        cvEntity.setSummery(newCv.getSummery());
        cvEntity.setSurname(newCv.getSurname());
        cvEntity.setUserByUserId(userEntity);
        cvEntity.setCvJobFieldByJobFieldId(cvJobFieldEntity);

        log.info("saving CvEntity");
        return getCvRepository().save(cvEntity);
    }

    public CvData findCv(int cvId) {
        String role = ApiUtil.getAuthentication().getAuthorities().stream().findFirst().get().getAuthority();
        String authUser = ApiUtil.getAuthUserName();
        CvEntity cvEntity = null;
        if (UserRoles.JOB_SEEKER.equals(role)) {
            log.info("getting CV by username:{}, cvId:{}", authUser, cvId);
            if (cvId == -1) {
                cvEntity = getCvRepository().findByUserName(authUser).orElseThrow(() ->
                        new ApiException(ResponseType.CV_NOT_FOUND, "CV not found for user: " + authUser)
                );
            } else {
                cvEntity = getCvRepository().findByUserNameAndCvId(authUser, cvId).orElseThrow(() ->
                        new ApiException(ResponseType.CV_NOT_FOUND, "CV not found for id: " + cvId)
                );
            }
        } else {
            cvEntity = getCvRepository().findById(cvId).orElseThrow(() ->
                    new ApiException(ResponseType.CV_NOT_FOUND, "CV not found for id: " + cvId)
            );
        }

        CvJobFieldEntity cvJobFieldEntity = cvEntity.getCvJobFieldByJobFieldId();
        CvData.CvJobField cvJobField = new CvData.CvJobField(cvJobFieldEntity.getId(), cvJobFieldEntity.getField());

        //set edu history list
        List<CvData.EduQualification> eduQualifications = cvEntity.getEducationHistoriesById().stream()
                .map(e -> {
                    return CvData.EduQualification.builder()
                            .from(e.getStartDate())
                            .to(e.getEndDate())
                            .institutionName(e.getInstitutionName())
                            .location(e.getLocation())
                            .id(e.getId())
                            .eduField(new CvData.EduQualification.EduField(
                                    e.getEducationStudyField().getId(),
                                    e.getEducationStudyField().getTitle())
                            )
                            .degreeLevel(new CvData.EduQualification.DegreeLevel(
                                    e.getDegreeLevel().getId(),
                                    e.getDegreeLevel().getLevel()
                            ))
                            .build();
                }).collect(Collectors.toList());

        //set work experience list
        List<CvData.WorkExperience> workExperiences = cvEntity.getWorkExperiencesById().stream()
                .map(e -> {
                    return CvData.WorkExperience.builder()
                            .employer(e.getEmployer())
                            .endDate(e.getEndDate())
                            .startDate(e.getStartDate())
                            .city(e.getCity())
                            .isCurrentJob(e.getCurrentJob())
                            .jobTitle(e.getJobTitle())
                            .country(e.getCountry())
                            .id(e.getId())
                            .employmentType(new CvData.WorkExperience.EmploymentType(
                                    e.getEmploymentType().getId(),
                                    e.getEmploymentType().getType()
                            ))
                            .build();
                }).collect(Collectors.toList());

        //set prof qualifications
        new ArrayList<>();
        List<CvData.ProfQualification> profQualifications = cvEntity.getProfessionalQualificationsById().stream()
                .map(e -> {
                    return CvData.ProfQualification.builder()
                            .description(e.getDescription())
                            .field(e.getField())
                            .title(e.getTitle())
                            .id(e.getId())
                            .build();
                }).collect(Collectors.toList());

        //set specific skills
        List<CvData.SpecificSkill> specificSkills = cvEntity.getSpecificSkilsById().stream()
                .map(e -> {
                    return CvData.SpecificSkill.builder()
                            .description(e.getDescription())
                            .id(e.getId())
                            .field(e.getField())
                            .title(e.getTitle())
                            .build();
                }).collect(Collectors.toList());

        return CvData.builder()
                .city(cvEntity.getCity())
                .contact_number(cvEntity.getContactNumber())
                .country(cvEntity.getCountry())
                .cvJobField(cvJobField)
                .email(cvEntity.getEmail())
                .id(cvEntity.getId())
                .first_name(cvEntity.getFirstName())
                .summery(cvEntity.getSummery())
                .surname(cvEntity.getSurname())
                .eduHistory(eduQualifications)
                .profQualifications(profQualifications)
                .specificSkills(specificSkills)
                .workExperiences(workExperiences)
                .build();
    }

    /**
     * deletes a cv
     * @param cvId
     */
    @Transactional
    public void permaDeleteCv(int cvId) {
        String authUser = ApiUtil.getAuthUserName();
        log.info("getting CV by username:{}, cvId:{}", authUser, cvId);
        CvEntity cvEntity = getCvRepository().findByUserNameAndCvId(authUser, cvId).orElseThrow(() ->
                new ApiException(ResponseType.CV_NOT_FOUND, "CV not found for id: " + cvId)
        );
        log.info("deleting cv|cvId:{}", cvEntity.getId());
        getCvRepository().deleteById(cvEntity.getId());
    }

    public void update(CvUpdateRequest request) {
        String user = ApiUtil.getAuthUserName();
        CvEntity entity = getCvRepository().findByUserNameAndCvId(user, request.getId()).<ApiException>orElseThrow(() -> {
            log.error("there's no cv to update");
            throw new ApiException(ResponseType.WORK_EXPERIENCE_NOT_FOUND, "there's no cv to update");
        });

        if (request.getFirstName() != null && !Objects.equals(request.getFirstName(), entity.getFirstName())) {
            entity.setFirstName(request.getFirstName());
        }

        if (request.getSurname() != null && !Objects.equals(request.getSurname(), entity.getSurname())) {
            entity.setSurname(request.getSurname());
        }

        if (request.getCountry() != null && !Objects.equals(request.getCountry(), entity.getCountry())) {
            entity.setCountry(request.getCountry());
        }

        if (request.getCity() != null && !Objects.equals(request.getCity(), entity.getCity())) {
            entity.setCity(request.getCity());
        }

        if (request.getEmail() != null && !Objects.equals(request.getEmail(), entity.getEmail())) {
            entity.setEmail(request.getEmail());
        }

        if (request.getContactNumber() != null && !Objects.equals(request.getContactNumber(), entity.getContactNumber())) {
            entity.setContactNumber(request.getContactNumber());
        }

        if (request.getSummery() != null && !Objects.equals(request.getSummery(), entity.getSummery())) {
            entity.setSummery(request.getSummery());
        }

        getCvRepository().save(entity);
    }

    public void submit(int cvId, boolean isDraft) {
        String user = ApiUtil.getAuthUserName();
        CvEntity entity = getCvRepository().findByUserNameAndCvId(user, cvId).<ApiException>orElseThrow(() -> {
            log.error("there's no cv to update");
            throw new ApiException(ResponseType.WORK_EXPERIENCE_NOT_FOUND, "there's no cv to update");
        });

        entity.setDraft(isDraft);
        getCvRepository().save(entity);
    }

    public GeneratedDocxDocDetail createDocx(int cvId) throws IOException {
        CvEntity cvData = getCvRepository().findById(cvId).orElseThrow(() ->
                new ApiException(ResponseType.CV_NOT_FOUND, "CV not found for id: " + cvId)
        );

        ByteArrayResource resource = FileUtil.generatePdf(cvData);
        return new GeneratedDocxDocDetail(
                resource,
                String.join("-", cvData.getFirstName(), cvData.getSurname(), String.valueOf(cvData.getId())) + ".pdf"
        );
    }
}
