package com.mycv.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "mycvdb", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "user_name", nullable = false, length = 255)
    private String userName;
    @Basic
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;
    @Basic
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<CvEntity> cvsById;
    @ManyToOne
    @JoinColumn(name = "user_role_id", referencedColumnName = "id")
    private UserRoleEntity userRoleByUserRoleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userName, that.userName) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(createdDate, that.createdDate) && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, email, createdDate, isActive);
    }

    public Collection<CvEntity> getCvsById() {
        return cvsById;
    }

    public void setCvsById(Collection<CvEntity> cvsById) {
        this.cvsById = cvsById;
    }

    public UserRoleEntity getUserRoleByUserRoleId() {
        return userRoleByUserRoleId;
    }

    public void setUserRoleByUserRoleId(UserRoleEntity userRoleByUserRoleId) {
        this.userRoleByUserRoleId = userRoleByUserRoleId;
    }
}
