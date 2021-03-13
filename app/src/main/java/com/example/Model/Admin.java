package com.example.Model;

import com.google.gson.annotations.SerializedName;

public class Admin {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("api_key")
    private String key;

    public Admin(){}

    public Admin(String name, String email, String key){
        this.name = name;
        this.email = email;
        this.key = key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
