package com.mycv.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "specific_skil", schema = "mycvdb", catalog = "")
public class SpecificSkilEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Basic
    @Column(name = "field", nullable = false, length = 255)
    private String field;

    @ManyToOne
    @JoinColumn(name = "cv_id", referencedColumnName = "id", nullable = false)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        SpecificSkilEntity that = (SpecificSkilEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, field);
    }

    public CvEntity getCvByCvId() {
        return cvByCvId;
    }

    public void setCvByCvId(CvEntity cvByCvId) {
        this.cvByCvId = cvByCvId;
    }
}
