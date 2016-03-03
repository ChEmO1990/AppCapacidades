package com.anselmo.appcapacidades.models;

/**
 * Created by anselmo on 2/29/16.
 */
public class DisabilityUser {
    private String address;
    private String cellphone;
    private String countFamily;
    private String dateBirthday;
    private String email;
    private String fatherLastname;
    private String gender;
    private String idUser;
    private String idUserFather;
    private String levelDisability;
    private String levelStudy;
    private String motherLastname;
    private String municipality;
    private String name;
    private String neighborhood;
    private String phone;
    private String typeDisability;
    private String objectId;

    public DisabilityUser() {}

    public DisabilityUser(String address, String cellphone, String countFamily, String dateBirthday, String email, String fatherLastname, String gender, String idUser, String idUserFather, String levelDisability, String levelStudy, String motherLastname, String municipality, String name, String neighborhood, String phone, String typeDisability, String objectId) {
        this.address = address;
        this.cellphone = cellphone;
        this.countFamily = countFamily;
        this.dateBirthday = dateBirthday;
        this.email = email;
        this.fatherLastname = fatherLastname;
        this.gender = gender;
        this.idUser = idUser;
        this.idUserFather = idUserFather;
        this.levelDisability = levelDisability;
        this.levelStudy = levelStudy;
        this.motherLastname = motherLastname;
        this.municipality = municipality;
        this.name = name;
        this.neighborhood = neighborhood;
        this.phone = phone;
        this.typeDisability = typeDisability;
        this.objectId = objectId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCountFamily() {
        return countFamily;
    }

    public void setCountFamily(String countFamily) {
        this.countFamily = countFamily;
    }

    public String getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(String dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatherLastname() {
        return fatherLastname;
    }

    public void setFatherLastname(String fatherLastname) {
        this.fatherLastname = fatherLastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUserFather() {
        return idUserFather;
    }

    public void setIdUserFather(String idUserFather) {
        this.idUserFather = idUserFather;
    }

    public String getLevelDisability() {
        return levelDisability;
    }

    public void setLevelDisability(String levelDisability) {
        this.levelDisability = levelDisability;
    }

    public String getLevelStudy() {
        return levelStudy;
    }

    public void setLevelStudy(String levelStudy) {
        this.levelStudy = levelStudy;
    }

    public String getMotherLastname() {
        return motherLastname;
    }

    public void setMotherLastname(String motherLastname) {
        this.motherLastname = motherLastname;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeDisability() {
        return typeDisability;
    }

    public void setTypeDisability(String typeDisability) {
        this.typeDisability = typeDisability;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}