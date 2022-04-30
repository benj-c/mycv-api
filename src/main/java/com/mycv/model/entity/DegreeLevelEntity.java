package com.mycv.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "degree_level", schema = "mycvdb", catalog = "")
public class DegreeLevelEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "level", nullable = false, length = 50)
    private String level;
    @OneToMany(mappedBy = "degreeLevelByDegreeLevelId")
    private Collection<EducationHistoryEntity> educationHistoriesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DegreeLevelEntity that = (DegreeLevelEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level);
    }

    public Collection<EducationHistoryEntity> getEducationHistoriesById() {
        return educationHistoriesById;
    }

    public void setEducationHistoriesById(Collection<EducationHistoryEntity> educationHistoriesById) {
        this.educationHistoriesById = educationHistoriesById;
    }
}
