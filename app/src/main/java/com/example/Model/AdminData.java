package com.example.Model;

import com.google.gson.annotations.SerializedName;

public class AdminData {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("jabatan")
    private String jabatan;
    @SerializedName("email")
    private String email;
    @SerializedName("no_hp")
    private String no_hp;
    @SerializedName("alamat")
    private String alamat;

    public AdminData (){}

    public AdminData(String name){
        this.name = name;
        this.jabatan = jabatan;
        this.email = email;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }
}
