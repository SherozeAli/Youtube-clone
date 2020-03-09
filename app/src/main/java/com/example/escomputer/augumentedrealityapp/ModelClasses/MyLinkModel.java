package com.example.escomputer.augumentedrealityapp.ModelClasses;

public class MyLinkModel {


    String title,link,description,usrid,imagestring,linkcounter,status,feature_status,category;

    public MyLinkModel() {
    }

    public MyLinkModel(String usrid, String link, String title, String description, String imagestring, String status, String linkcounter, String feature_status, String category) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.usrid = usrid;
        this.imagestring = imagestring;
        this.linkcounter = linkcounter;
        this.status = status;
        this.feature_status = feature_status;
        this.category = category;
    }

    public MyLinkModel(String usrid, String link, String title, String description, String imagestring, String status, String linkcounter, String feature_status) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.usrid = usrid;
        this.imagestring = imagestring;
        this.linkcounter = linkcounter;
        this.status = status;
        this.feature_status = feature_status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFeature_status() {
        return feature_status;
    }

    public void setFeature_status(String feature_status) {
        this.feature_status = feature_status;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public String getImagestring() {
        return imagestring;
    }

    public void setImagestring(String imagestring) {
        this.imagestring = imagestring;
    }

    public String getLinkcounter() {
        return linkcounter;
    }

    public void setLinkcounter(String linkcounter) {
        this.linkcounter = linkcounter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
