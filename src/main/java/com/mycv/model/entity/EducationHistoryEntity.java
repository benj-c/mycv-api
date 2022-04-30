package com.mycv.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "education_history", schema = "mycvdb", catalog = "")
public class EducationHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "institution_name", nullable = false, length = 255)
    private String institutionName;
    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Basic
    @Column(name = "location", nullable = false, length = 255)
    private String location;
    @Basic
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Basic
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @ManyToOne
    @JoinColumn(name = "degree_level_id", referencedColumnName = "ID")
    private DegreeLevelEntity degreeLevelByDegreeLevelId;
    @ManyToOne
    @JoinColumn(name = "education_study_field_id", referencedColumnName = "id")
    private EducationStudyFieldEntity educationStudyFieldByEducationStudyFieldId;
    @ManyToOne
    @JoinColumn(name = "cv_id", referencedColumnName = "id")
    private CvEntity cvByCvId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate from) {
        this.startDate = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationHistoryEntity entity = (EducationHistoryEntity) o;
        return Objects.equals(id, entity.id) && Objects.equals(institutionName, entity.institutionName) && Objects.equals(location, entity.location) && Objects.equals(endDate, entity.endDate) && Objects.equals(startDate, entity.startDate) && Objects.equals(title, entity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, institutionName, location, endDate, startDate, title);
    }

    public DegreeLevelEntity getDegreeLevel() {
        return degreeLevelByDegreeLevelId;
    }

    public void setDegreeLevel(DegreeLevelEntity degreeLevelByDegreeLevelId) {
        this.degreeLevelByDegreeLevelId = degreeLevelByDegreeLevelId;
    }

    public EducationStudyFieldEntity getEducationStudyField() {
        return educationStudyFieldByEducationStudyFieldId;
    }

    public void setEducationStudyField(EducationStudyFieldEntity educationStudyFieldByEducationStudyFieldId) {
        this.educationStudyFieldByEducationStudyFieldId = educationStudyFieldByEducationStudyFieldId;
    }

    public CvEntity getCvByCvId() {
        return cvByCvId;
    }

    public void setCvByCvId(CvEntity cvByCvId) {
        this.cvByCvId = cvByCvId;
    }
}
