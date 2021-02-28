package com.example.Model;

import com.google.gson.annotations.SerializedName;

public class Admin {
    @SerializedName("name")
    private String name;
    @SerializedName("api_key")
    private String key;

    public Admin(){}

    public Admin(String name, String key){
        this.name = name;
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

}
