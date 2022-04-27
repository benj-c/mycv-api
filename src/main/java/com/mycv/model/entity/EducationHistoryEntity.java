package com.mycv.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "education_history", schema = "mycvdb", catalog = "")
public class EducationHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "instituition_name", nullable = false, length = 255)
    private String instituitionName;

    @Column(name = "location", nullable = false, length = 255)
    private String location;

    @Column(name = "awarded_date", nullable = false)
    private Date awardedDate;

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

    public String getInstituitionName() {
        return instituitionName;
    }

    public void setInstituitionName(String instituitionName) {
        this.instituitionName = instituitionName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getAwardedDate() {
        return awardedDate;
    }

    public void setAwardedDate(Date awardedDate) {
        this.awardedDate = awardedDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationHistoryEntity that = (EducationHistoryEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(instituitionName, that.instituitionName) && Objects.equals(location, that.location) && Objects.equals(awardedDate, that.awardedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instituitionName, location, awardedDate);
    }

    public EducationStudyFieldEntity getEducationStudyFieldByEducationStudyFieldId() {
        return educationStudyFieldByEducationStudyFieldId;
    }

    public void setEducationStudyFieldByEducationStudyFieldId(EducationStudyFieldEntity educationStudyFieldByEducationStudyFieldId) {
        this.educationStudyFieldByEducationStudyFieldId = educationStudyFieldByEducationStudyFieldId;
    }

    public CvEntity getCvByCvId() {
        return cvByCvId;
    }

    public void setCvByCvId(CvEntity cvByCvId) {
        this.cvByCvId = cvByCvId;
    }
}
