package com.mycv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "education_study_field", schema = "mycvdb", catalog = "")
public class EducationStudyFieldEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @JsonIgnore
    @OneToMany(mappedBy = "educationStudyFieldByEducationStudyFieldId")
    private Collection<EducationHistoryEntity> educationHistoriesById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationStudyFieldEntity that = (EducationStudyFieldEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    public Collection<EducationHistoryEntity> getEducationHistoriesById() {
        return educationHistoriesById;
    }

    public void setEducationHistoriesById(Collection<EducationHistoryEntity> educationHistoriesById) {
        this.educationHistoriesById = educationHistoriesById;
    }
}
