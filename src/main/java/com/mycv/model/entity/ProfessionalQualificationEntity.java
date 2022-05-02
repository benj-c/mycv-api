package com.mycv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "professional_qualification", schema = "mycvdb", catalog = "")
public class ProfessionalQualificationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Basic
    @Column(name = "field", nullable = false, length = 255)
    private String field;
    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cv_id", referencedColumnName = "id")
    private CvEntity cvByCvId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessionalQualificationEntity entity = (ProfessionalQualificationEntity) o;
        return Objects.equals(id, entity.id) && Objects.equals(title, entity.title) && Objects.equals(field, entity.field) && Objects.equals(description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, field, description);
    }

    public CvEntity getCvByCvId() {
        return cvByCvId;
    }

    public void setCvByCvId(CvEntity cvByCvId) {
        this.cvByCvId = cvByCvId;
    }
}
