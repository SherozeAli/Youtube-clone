package com.example.escomputer.augumentedrealityapp.ModelClasses;

public class UserInformation {
String name,email,passwaord,phone,address,profilepic;


  public UserInformation(){


   }
   public UserInformation(String name, String email, String passwaord, String phone, String address, String profilepic) {
        this.name = name;
        this.email = email;
        this.passwaord = passwaord;
        this.phone = phone;
        this.address = address;
        this.profilepic = profilepic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswaord() {
        return passwaord;
    }

    public void setPasswaord(String passwaord) {
        this.passwaord = passwaord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }
}
