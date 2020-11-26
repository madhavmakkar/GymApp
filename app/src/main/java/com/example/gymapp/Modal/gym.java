package com.example.gymapp.Modal;

public class gym {
    private String username, height,weight;
    public gym(){

    }

    public gym(String username,  String height, String weight) {
        this.username = username;
        this.height = height;
        this.weight = weight;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}