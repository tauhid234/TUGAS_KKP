package com.example.Rest;

import com.example.Model.Admin;
import com.google.gson.annotations.SerializedName;

public class Method {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Admin mAdmin;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Admin getAdmin() {
        return mAdmin;
    }
    public void setAdmin(Admin Admin) {
        mAdmin = Admin;
    }
}
