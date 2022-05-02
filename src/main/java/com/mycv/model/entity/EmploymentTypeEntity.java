package com.mycv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "employment_type", schema = "mycvdb", catalog = "")
public class EmploymentTypeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "type", nullable = false, length = 50)
    private String type;
    @JsonIgnore
    @OneToMany(mappedBy = "employmentTypeByEmploymentTypeId")
    private Collection<WorkExperienceEntity> workExperiencesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmploymentTypeEntity that = (EmploymentTypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    public Collection<WorkExperienceEntity> getWorkExperiencesById() {
        return workExperiencesById;
    }

    public void setWorkExperiencesById(Collection<WorkExperienceEntity> workExperiencesById) {
        this.workExperiencesById = workExperiencesById;
    }
}
