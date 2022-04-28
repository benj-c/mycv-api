package com.mycv.model.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "education_history", schema = "mycvdb", catalog = "")
public class EducationHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "institution_name", nullable = false, length = 255)
    private String institutionName;

    @Column(name = "location", nullable = false, length = 255)
    private String location;

    @Column(name = "awarded_date", nullable = false)
    private LocalDate awardedDate;

    @ManyToOne
    @JoinColumn(name = "education_study_field_id", referencedColumnName = "id")
    private EducationStudyFieldEntity educationStudyField;

    @ManyToOne
    @JoinColumn(name = "cv_id", referencedColumnName = "id", updatable = false)
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getAwardedDate() {
        return awardedDate;
    }

    public void setAwardedDate(LocalDate awardedDate) {
        this.awardedDate = awardedDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationHistoryEntity that = (EducationHistoryEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(institutionName, that.institutionName) && Objects.equals(location, that.location) && Objects.equals(awardedDate, that.awardedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, institutionName, location, awardedDate);
    }

    public EducationStudyFieldEntity getEducationStudyField() {
        return educationStudyField;
    }

    public void setEducationStudyField(EducationStudyFieldEntity educationStudyField) {
        this.educationStudyField = educationStudyField;
    }

    public CvEntity getCvByCvId() {
        return cvByCvId;
    }

    public void setCvByCvId(CvEntity cvByCvId) {
        this.cvByCvId = cvByCvId;
    }
}
