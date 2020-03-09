package com.example.escomputer.augumentedrealityapp.ModelClasses;

public class FavouriteModel {
    String id,status,titile,des,link,cat,image,fav_status,linkcounter;

    public FavouriteModel() {
    }

    public FavouriteModel(String id, String status, String titile, String des, String link, String cat, String image, String linkcounter) {
        this.id = id;
        this.status = status;
        this.titile = titile;
        this.des = des;
        this.link = link;
        this.cat = cat;
        this.image = image;
        this.linkcounter = linkcounter;
    }

    public FavouriteModel(String id, String status, String titile, String des, String link, String cat, String image, String fav_status, String linkcounter) {
        this.id = id;
        this.status = status;
        this.titile = titile;
        this.des = des;
        this.link = link;
        this.cat = cat;
        this.image = image;
        this.fav_status = fav_status;
        this.linkcounter = linkcounter;
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

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFav_status() {
        return fav_status;
    }

    public void setFav_status(String fav_status) {
        this.fav_status = fav_status;
    }

    public String getLinkcounter() {
        return linkcounter;
    }

    public void setLinkcounter(String linkcounter) {
        this.linkcounter = linkcounter;
    }
}
