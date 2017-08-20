package com.sekapato.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * An entity User composed by three fields (id, email, name). The Entity
 * annotation indicates that this class is a JPA entity. The Table annotation
 * specifies the name for the table in the db.
 *
 * @author netgloo
 */
@Entity
@Table(name = "users")
public class User {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // An autogenerated id (unique for each user in the db)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    // The user's name
    @NotNull
    public String name;

    public String kana;

    public String nickname;

    public Integer age;

    public String address;

    public String password;

    public String email;

    public String tel;

    public String lineId;

    public Boolean identificationFlg;

    public String userClass;

    public String occupation;

    public Integer point;

    public String comment;

    public Boolean deleteFlg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKana() {
        return kana;
    }

    public void setKana(String kana) {
        this.kana = kana;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public Boolean getIdentificationFlg() {
        return identificationFlg;
    }

    public void setIdentificationFlg(Boolean identificationFlg) {
        this.identificationFlg = identificationFlg;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(Boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", kana=" + kana + ", nickname=" + nickname + ", age=" + age
                + ", address=" + address + ", password=" + password + ", email=" + email + ", tel=" + tel + ", lineId="
                + lineId + ", identificationFlg=" + identificationFlg + ", userClass=" + userClass + ", occupation="
                + occupation + ", point=" + point + ", comment=" + comment + ", deleteFlg=" + deleteFlg + "]";
    }

    

}
