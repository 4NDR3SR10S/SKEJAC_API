package com.corporated.skejac.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_ample")
@AllArgsConstructor
@NoArgsConstructor
public class UserXampleEntity {

    @Id
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "frst_name")
    private String frstName;

    @Column(name = "lst_name")
    private String lstName;

    @Column (name ="email" )
    private String email;

    @Column (name = "ammount")
    private Integer ammount;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFrstName() {
        return frstName;
    }

    public void setFrstName(String frstName) {
        this.frstName = frstName;
    }

    public String getLstName() {
        return lstName;
    }

    public void setLstName(String lstName) {
        this.lstName = lstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAmmount() {
        return ammount;
    }

    public void setAmmount(Integer ammount) {
        this.ammount = ammount;
    }
}
