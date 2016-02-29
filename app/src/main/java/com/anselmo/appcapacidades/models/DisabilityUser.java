package com.anselmo.appcapacidades.models;

/**
 * Created by anselmo on 2/29/16.
 */
public class DisabilityUser {
    private String name;
    private String fatherLastname;
    private String typeDisability;
    private String levelDisability;
    private String dateBirthday;
    private String gender;

    public DisabilityUser() {}

    public DisabilityUser(String name, String fatherLastname, String typeDisability, String levelDisability, String dateBirthday, String gender) {
        this.name = name;
        this.fatherLastname = fatherLastname;
        this.typeDisability = typeDisability;
        this.levelDisability = levelDisability;
        this.dateBirthday = dateBirthday;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherLastname() {
        return fatherLastname;
    }

    public void setFatherLastname(String fatherLastname) {
        this.fatherLastname = fatherLastname;
    }

    public String getTypeDisability() {
        return typeDisability;
    }

    public void setTypeDisability(String typeDisability) {
        this.typeDisability = typeDisability;
    }

    public String getLevelDisability() {
        return levelDisability;
    }

    public void setLevelDisability(String levelDisability) {
        this.levelDisability = levelDisability;
    }

    public String getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(String dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
