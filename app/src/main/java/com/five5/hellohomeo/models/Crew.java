package com.five5.hellohomeo.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "crew")
public class Crew {
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="agency")
    private String agency;
    @ColumnInfo(name="image")
    private String image;
    @ColumnInfo(name="wikipedia")
    private String wikipedia;
    @ColumnInfo(name="status")
    private String status;
    public Crew(String name, String agency, String image, String wikipedia, String id, String status) {
        this.name = name;
        this.agency = agency;
        this.image = image;
        this.wikipedia = wikipedia;
        this.id = id;
        this.status = status;
    }

    public Crew() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
