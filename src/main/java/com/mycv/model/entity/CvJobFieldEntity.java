package com.mycv.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cv_job_field", schema = "mycvdb", catalog = "")
public class CvJobFieldEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "field", nullable = false, length = 255)
    private String field;
    @OneToMany(mappedBy = "cvJobFieldByJobFieldId")
    private Collection<CvEntity> cvsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CvJobFieldEntity that = (CvJobFieldEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, field);
    }

    public Collection<CvEntity> getCvsById() {
        return cvsById;
    }

    public void setCvsById(Collection<CvEntity> cvsById) {
        this.cvsById = cvsById;
    }
}
