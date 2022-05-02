package com.mycv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "work_experience", schema = "mycvdb", catalog = "")
public class WorkExperienceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "job_title", nullable = false, length = 255)
    private String jobTitle;
    @Basic
    @Column(name = "country", nullable = false, length = 255)
    private String country;
    @Basic
    @Column(name = "employer", nullable = false, length = 255)
    private String employer;
    @Basic
    @Column(name = "city", nullable = false, length = 255)
    private String city;
    @Basic
    @Column(name = "is_current_job", nullable = false)
    private Boolean isCurrentJob;
    @Basic
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Basic
    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cv_id", referencedColumnName = "id")
    private CvEntity cvByCvId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employment_type_id", referencedColumnName = "id")
    private EmploymentTypeEntity employmentTypeByEmploymentTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(Boolean currentJob) {
        isCurrentJob = currentJob;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkExperienceEntity that = (WorkExperienceEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(country, that.country) && Objects.equals(employer, that.employer) && Objects.equals(city, that.city) && Objects.equals(isCurrentJob, that.isCurrentJob) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobTitle, country, employer, city, isCurrentJob, startDate, endDate);
    }

    public CvEntity getCvByCvId() {
        return cvByCvId;
    }

    public void setCvByCvId(CvEntity cvByCvId) {
        this.cvByCvId = cvByCvId;
    }

    public EmploymentTypeEntity getEmploymentType() {
        return employmentTypeByEmploymentTypeId;
    }

    public void setEmploymentType(EmploymentTypeEntity employmentTypeByEmploymentTypeId) {
        this.employmentTypeByEmploymentTypeId = employmentTypeByEmploymentTypeId;
    }
}
