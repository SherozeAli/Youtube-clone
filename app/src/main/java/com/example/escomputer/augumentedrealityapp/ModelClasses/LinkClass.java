package com.example.escomputer.augumentedrealityapp.ModelClasses;

public class LinkClass {
    private String link,user_id,user_description,user_title,link_image,approved_status,linkCounter,feature_status,category;

    public LinkClass() {

    }

    public LinkClass(String user_id, String link, String user_title, String user_description, String link_image, String approved_status, String linkCounter, String feature_status, String category) {
        this.link = link;
        this.user_id = user_id;
        this.user_description = user_description;
        this.user_title = user_title;
        this.link_image = link_image;
        this.approved_status = approved_status;
        this.linkCounter = linkCounter;
        this.feature_status = feature_status;
        this.category = category;
    }

    public LinkClass(String user_id, String link, String user_title, String user_description, String link_image, String approved_status, String linkCounter, String feature_status) {
        this.link = link;
        this.user_id = user_id;
        this.user_description = user_description;
        this.user_title = user_title;
        this.link_image = link_image;
        this.approved_status = approved_status;
        this.linkCounter = linkCounter;
        this.feature_status = feature_status;
    }


    public String getApproved_status() {
        return approved_status;
    }

    public void setApproved_status(String approved_status) {
        this.approved_status = approved_status;
    }

    public String getFeature_status() {
        return feature_status;
    }

    public void setFeature_status(String feature_status) {
        this.feature_status = feature_status;
    }

    public String getLinkCounter() {
        return linkCounter;
    }

    public void setLinkCounter(String linkCounter) {
        this.linkCounter = linkCounter;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;


    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUser_image() {
        return link_image;
    }

    public void setUser_image(String user_image) {
        this.link_image = user_image;
    }
}
