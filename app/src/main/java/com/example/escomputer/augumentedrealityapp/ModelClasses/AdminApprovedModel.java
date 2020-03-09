package com.example.escomputer.augumentedrealityapp.ModelClasses;

 public class AdminApprovedModel {
String approved_image,approved_title,approved_description,approved_link,approved_userID,admin_counter,admin_approvedStatus;

    public AdminApprovedModel() {
    }

     public AdminApprovedModel(String approved_userID, String approved_link,String approved_title, String approved_description, String approved_image, String admin_approvedStatus, String admin_counter) {
         this.approved_image = approved_image;
         this.approved_title = approved_title;
         this.approved_description = approved_description;
         this.approved_link = approved_link;
         this.approved_userID = approved_userID;
         this.admin_counter = admin_counter;
         this.admin_approvedStatus = admin_approvedStatus;
     }

     public AdminApprovedModel(String approved_image, String approved_title, String approved_description, String approved_link, String approved_userID) {
         this.approved_image = approved_image;
         this.approved_title = approved_title;
         this.approved_description = approved_description;
         this.approved_link = approved_link;
         this.approved_userID = approved_userID;
     }

     public AdminApprovedModel(String approved_image, String approved_title, String approved_description, String approved_link) {
         this.approved_image = approved_image;
         this.approved_title = approved_title;
         this.approved_description = approved_description;
         this.approved_link = approved_link;
     }

     public String getApproved_userID() {
         return approved_userID;
     }

     public void setApproved_userID(String approved_userID) {
         this.approved_userID = approved_userID;
     }

     public String getAdmin_counter() {
         return admin_counter;
     }

     public void setAdmin_counter(String admin_counter) {
         this.admin_counter = admin_counter;
     }

     public String getAdmin_approvedStatus() {
         return admin_approvedStatus;
     }

     public void setAdmin_approvedStatus(String admin_approvedStatus) {
         this.admin_approvedStatus = admin_approvedStatus;
     }

     public String getApproved_image() {
        return approved_image;
    }

    public void setApproved_image(String approved_image) {
        this.approved_image = approved_image;
    }

    public String getApproved_title() {
        return approved_title;
    }

    public void setApproved_title(String approved_title) {
        this.approved_title = approved_title;
    }

    public String getApproved_description() {
        return approved_description;
    }

    public void setApproved_description(String approved_description) {
        this.approved_description = approved_description;
    }

    public String getApproved_link() {
        return approved_link;
    }

    public void setApproved_link(String approved_link) {
        this.approved_link = approved_link;
    }
}
