package com.mycv.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cv", schema = "mycvdb", catalog = "")
public class CvEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;
    @Basic
    @Column(name = "is_draft", nullable = false)
    private Boolean isDraft;
    @Basic
    @Column(name = "summery", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String summery;
    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;
    @Basic
    @Column(name = "surname", nullable = false, length = 255)
    private String surname;
    @Basic
    @Column(name = "country", nullable = false, length = 255)
    private String country;
    @Basic
    @Column(name = "city", nullable = false, length = 255)
    private String city;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "contact_number", nullable = false, length = 15)
    private String contactNumber;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userByUserId;
    @ManyToOne
    @JoinColumn(name = "job_field_id", referencedColumnName = "id")
    private CvJobFieldEntity cvJobFieldByJobFieldId;
    @OneToMany(mappedBy = "cvByCvId")
    private Collection<EducationHistoryEntity> educationHistoriesById;
    @OneToMany(mappedBy = "cvByCvId")
    private Collection<ProfessionalQualificationEntity> professionalQualificationsById;
    @OneToMany(mappedBy = "cvByCvId")
    private Collection<SpecificSkilEntity> specificSkilsById;
    @OneToMany(mappedBy = "cvByCvId")
    private Collection<WorkExperienceEntity> workExperiencesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean draft) {
        isDraft = draft;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CvEntity cvEntity = (CvEntity) o;
        return Objects.equals(id, cvEntity.id) && Objects.equals(createdDate, cvEntity.createdDate) && Objects.equals(isDraft, cvEntity.isDraft) && Objects.equals(summery, cvEntity.summery) && Objects.equals(firstName, cvEntity.firstName) && Objects.equals(surname, cvEntity.surname) && Objects.equals(country, cvEntity.country) && Objects.equals(city, cvEntity.city) && Objects.equals(email, cvEntity.email) && Objects.equals(contactNumber, cvEntity.contactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, isDraft, summery, firstName, surname, country, city, email, contactNumber);
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public CvJobFieldEntity getCvJobFieldByJobFieldId() {
        return cvJobFieldByJobFieldId;
    }

    public void setCvJobFieldByJobFieldId(CvJobFieldEntity cvJobFieldByJobFieldId) {
        this.cvJobFieldByJobFieldId = cvJobFieldByJobFieldId;
    }

    public Collection<EducationHistoryEntity> getEducationHistoriesById() {
        return educationHistoriesById;
    }

    public void setEducationHistoriesById(Collection<EducationHistoryEntity> educationHistoriesById) {
        this.educationHistoriesById = educationHistoriesById;
    }

    public Collection<ProfessionalQualificationEntity> getProfessionalQualificationsById() {
        return professionalQualificationsById;
    }

    public void setProfessionalQualificationsById(Collection<ProfessionalQualificationEntity> professionalQualificationsById) {
        this.professionalQualificationsById = professionalQualificationsById;
    }

    public Collection<SpecificSkilEntity> getSpecificSkilsById() {
        return specificSkilsById;
    }

    public void setSpecificSkilsById(Collection<SpecificSkilEntity> specificSkilsById) {
        this.specificSkilsById = specificSkilsById;
    }

    public Collection<WorkExperienceEntity> getWorkExperiencesById() {
        return workExperiencesById;
    }

    public void setWorkExperiencesById(Collection<WorkExperienceEntity> workExperiencesById) {
        this.workExperiencesById = workExperiencesById;
    }
}
